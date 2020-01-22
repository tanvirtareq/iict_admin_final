package com.example.iictcafeadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<OrderDetails> arrayList;
    private FirebaseRecyclerOptions<OrderDetails> options;
    private FirebaseRecyclerAdapter<OrderDetails, DataViewHolder> adapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders);

        //toolbar = findViewById(R.id.maintoolbar);

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("IICT Cafe-Admin");

        recyclerView = findViewById(R.id.pendingreCyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("dibba");
        databaseReference.keepSynced(true);

        options = new FirebaseRecyclerOptions.Builder<OrderDetails>().setQuery(databaseReference, OrderDetails.class).build();

        adapter = new FirebaseRecyclerAdapter<OrderDetails, DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder( DataViewHolder holder, int position, @NonNull final OrderDetails model) {
                holder.nameview.setText(model.getDisplay_name());
                holder.tableview.setText(model.getTableno());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(LogActivity.this, "Order for Table "+model.getTableno(), Toast.LENGTH_LONG).show();

                        AlertDialog.Builder builder = new AlertDialog.Builder(LogActivity.this);
                        builder.setTitle("Order from " + model.getDisplay_name());

//                        builder.setPositiveButton("DELIVER", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("delivery")
//                                        .child(model.getOid());
//
//                                ref.setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if(task.isSuccessful()){
//                                            databaseReference.child(model.getOid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    if(task.isSuccessful()){
//                                                        Toast.makeText(LogActivity.this, "Order Delivered", Toast.LENGTH_LONG).show();
//                                                    }
//                                                }
//                                            });
//                                        }
//                                    }
//                                });
//                            }
//                        });
//
//                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });

                        builder.setMessage(model.getDetails());
                        AlertDialog dialog = builder.create();
                        dialog.show();


                    }
                });
            }

            @NonNull
            @Override
            public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new DataViewHolder(LayoutInflater.from(LogActivity.this).inflate(R.layout.model, viewGroup, false));
            }
        };

        recyclerView.setAdapter(adapter);



    }

}

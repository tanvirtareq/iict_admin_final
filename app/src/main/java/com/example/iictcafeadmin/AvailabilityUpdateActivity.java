package com.example.iictcafeadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AvailabilityUpdateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<OrderDetails> arrayList;
    private FirebaseRecyclerOptions<UpdateItem> options;
    private FirebaseRecyclerAdapter<UpdateItem, MenuViewHolder> adapter;
    private DatabaseReference ref;

    private AlertDialog.Builder mBuilder;
    private View mView;
    private Button okButton, cancelButton;
    private Spinner spinner;

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
        setContentView(R.layout.activity_availability_update);

        recyclerView = findViewById(R.id.update_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ref = FirebaseDatabase.getInstance().getReference().child("food");
        ref.keepSynced(true);

        arrayList = new ArrayList<>();

        options = new FirebaseRecyclerOptions.Builder<UpdateItem>().setQuery(ref, UpdateItem.class).build();
        adapter = new FirebaseRecyclerAdapter<UpdateItem, MenuViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder holder, int i, @NonNull UpdateItem updateItem) {

                final boolean flag = updateItem.isAvaibality();
                final String itemid = updateItem.getItem_id();

                holder.itemName.setText(updateItem.getItem_name());
                if(!flag) holder.updateBtn.setText("Set Available");
                else holder.updateBtn.setText("Set Unavailable");

                holder.updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseDatabase.getInstance().getReference()
                                .child("food").child(itemid).child("avaibality")
                                .setValue(!flag)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        if(flag) Toast.makeText(AvailabilityUpdateActivity.this, "Availability set to false" , Toast.LENGTH_LONG).show();
                                        else Toast.makeText(AvailabilityUpdateActivity.this, "Availability set to false" , Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                });

                /*holder.updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBuilder = new AlertDialog.Builder(AvailabilityUpdateActivity.this);
                        mView = getLayoutInflater().inflate(R.layout.dialog_update_menu, null);

                        okButton = mView.findViewById(R.id.ok_button);
                        cancelButton = mView.findViewById(R.id.cancel_button);
                        spinner = mView.findViewById(R.id.spinner);

                        //spinner;
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AvailabilityUpdateActivity.this, R.array.spinner_items, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(AvailabilityUpdateActivity.this);

                        mBuilder.setView(mView);
                        final AlertDialog alertDialog = mBuilder.create();
                        alertDialog.show();

                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                            }
                        });

                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                    }
                });*/
            }

            @NonNull
            @Override
            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MenuViewHolder(LayoutInflater.from(AvailabilityUpdateActivity.this).inflate(R.layout.menu_view_holder, parent, false));
            }


        };

        recyclerView.setAdapter(adapter);
    }
}

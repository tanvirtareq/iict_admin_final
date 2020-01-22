package com.example.iictcafeadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import static android.content.Context.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView logCardViewId, updateMenuCardViewId, rechargeCardViewId, pendingOrdersCardviewId;


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful()){
                            FirebaseDatabase.getInstance().getReference().child("admin_token")
                                    .setValue(task.getResult().getToken()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            });
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logCardViewId=findViewById(R.id.log);
        updateMenuCardViewId=findViewById(R.id.updateMenuCardView);
        rechargeCardViewId=findViewById(R.id.rechargeCardView);
        pendingOrdersCardviewId=findViewById(R.id.pendinOrders);

        logCardViewId.setOnClickListener(this);
        updateMenuCardViewId.setOnClickListener(this);
        rechargeCardViewId.setOnClickListener(this);
        pendingOrdersCardviewId.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.log)
        {
            Intent intent=new Intent(MainActivity.this, LogActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.updateMenuCardView)
        {

            Intent intent=new Intent(MainActivity.this, UpdateMenuActivity.class);
            startActivity(intent);

        }
        if(v.getId()==R.id.rechargeCardView)
        {

            Intent intent=new Intent(MainActivity.this, RechargeActivity.class);
            startActivity(intent);

        }

        if(v.getId()==R.id.pendinOrders)
        {

            Intent intent=new Intent(MainActivity.this, PendingOrdersActivity.class);
            startActivity(intent);

        }

    }
}

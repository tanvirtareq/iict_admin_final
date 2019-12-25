package com.example.iictcafeadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView logCardViewId, updateMenuCardViewId, rechargeCardViewId, pendingOrdersCardviewId;

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

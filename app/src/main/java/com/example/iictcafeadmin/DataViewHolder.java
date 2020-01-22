package com.example.iictcafeadmin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataViewHolder extends RecyclerView.ViewHolder {
    public TextView nameview, tableview;
    public DataViewHolder(@NonNull View itemView) {
        super(itemView);

        nameview = itemView.findViewById(R.id.nameView);
        tableview = itemView.findViewById(R.id.tableno);
    }
}
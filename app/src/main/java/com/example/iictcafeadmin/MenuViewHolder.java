package com.example.iictcafeadmin;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    public TextView itemName;
    public Button updateBtn;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        itemName = itemView.findViewById(R.id.itemNameView);
       // updateBtn = itemView.findViewById(R.id.updateMenuButton);
    }
}

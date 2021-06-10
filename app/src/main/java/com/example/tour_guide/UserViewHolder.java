package com.example.tour_guide;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView shname,shsubj;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        shname = itemView.findViewById(R.id.tuname);
        shsubj = itemView.findViewById(R.id.tusubj);
    }
}

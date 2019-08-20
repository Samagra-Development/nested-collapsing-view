package com.samagra.nested_collapsing_view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

abstract class CustomViewHolder extends RecyclerView.ViewHolder implements CollapsingViewHolder {

    CustomViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}

package com.samagra.nested_collapsing_view.test;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AbstractBindedViewHolder extends RecyclerView.ViewHolder implements BindableViewHolder {

    public AbstractBindedViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}

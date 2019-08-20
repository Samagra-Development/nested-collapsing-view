package com.samagra.nested_collapsing_view;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface CollapsingViewHolder<VH extends RecyclerView.ViewHolder & CollapsingViewHolder> {

    VH getAsRecyclerViewHolder(View itemView);
}

package com.samagra.nested_collapsing_view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public interface BindedLevelItem<T, VH extends RecyclerView.ViewHolder> {

    void onBindViewHolder(@NonNull VH viewHolder, int position);
}

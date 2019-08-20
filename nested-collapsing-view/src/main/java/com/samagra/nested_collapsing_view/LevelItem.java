package com.samagra.nested_collapsing_view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class LevelItem<T, VH extends RecyclerView.ViewHolder & CollapsingViewHolder> implements BindedLevelItem<T, VH> {
    private T item;
    private VH viewHolder;
    private int layoutRes;
    private BindedLevelItem<T, VH> bindedLevelItem;

    LevelItem(int layoutRes, T item, VH viewHolder, BindedLevelItem<T, VH> bindedLevelItem) {
        this.layoutRes = layoutRes;
        this.item = item;
        this.viewHolder = viewHolder;
        this.bindedLevelItem = bindedLevelItem;
    }

    public T getItem() {
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull VH viewHolder, int position) {
        bindedLevelItem.onBindViewHolder(viewHolder, position);
    }
}

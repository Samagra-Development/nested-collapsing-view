package com.samagra.nested_collapsing_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class LevelItemAdapter<VH extends CustomViewHolder> extends RecyclerView.Adapter<VH> {

    private VH viewHolder;
    private int layoutRes;
    private ArrayList<BindedLevelItem> items;

    LevelItemAdapter(BindedLevelItem[] items, int layoutRes, VH viewHolder) {
        this.items = new ArrayList<>(Arrays.asList(items));
        this.layoutRes = layoutRes;
        this.viewHolder = viewHolder;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return (VH) viewHolder.getAsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (items.get(position) != null) {
            items.get(position).onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList<BindedLevelItem> getItems() {
        return items;
    }
}

package com.samagra.nested_collapsing_view.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samagra.nested_collapsing_view.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HierarchyViewAdapter<VH extends AbstractBindedViewHolder> extends RecyclerView.Adapter<VH> {

    private ArrayList<HierarchyViewItem> viewItems;
    private int layoutResForThisLevel;
    private ViewHolderSupplier<? extends VH> viewHolderSupplier;
    private int depth = 0;
    private boolean expanded = false;

    public HierarchyViewAdapter(@NonNull ArrayList<HierarchyViewItem> viewItems, int layoutResForThisLevel,
                                ViewHolderSupplier<? extends VH> viewHolderSupplier) {
        this.viewItems = viewItems;
        this.layoutResForThisLevel = layoutResForThisLevel;
        this.viewHolderSupplier = viewHolderSupplier;
    }

    private HierarchyViewAdapter(@NonNull ArrayList<HierarchyViewItem> viewItems, int layoutResForThisLevel,
                                 ViewHolderSupplier<? extends VH> viewHolderSupplier, int depth) {
        this.viewItems = viewItems;
        this.layoutResForThisLevel = layoutResForThisLevel;
        this.viewHolderSupplier = viewHolderSupplier;
        this.depth = depth;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResForThisLevel, parent, false);
        return viewHolderSupplier.get(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, int position) {
        final HierarchyViewItem hierarchyViewItem = viewItems.get(position);
        HashMap<String, Object> viewItemMapping = hierarchyViewItem.getViewMapping();
        for (Map.Entry<String, Object> entry : viewItemMapping.entrySet()) {
            holder.bindView(entry.getKey(), entry.getValue().toString());
        }
        if (hierarchyViewItem.getChildren() != null && hierarchyViewItem.getChildren().size() > 0) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!expanded) {
                        // TODO : Inflate and add RecyclerView
                        View recyclerTemplate = LayoutInflater.from(view.getContext()).inflate(
                                R.layout.recycler_view_template, holder.getViewGroup(), false);
                        holder.getViewGroup().addView(recyclerTemplate);
                        holder.setupRecyclerView(hierarchyViewItem.getChildren(),
                                (RecyclerView) recyclerTemplate.findViewById(R.id.recycler_view),
                                ++depth);
                    } else {
                        // TODO : Remove the expanded recycler view
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }
}

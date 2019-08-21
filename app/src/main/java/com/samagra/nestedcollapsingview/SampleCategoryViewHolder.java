package com.samagra.nestedcollapsingview;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samagra.nested_collapsing_view.test.AbstractBindedViewHolder;
import com.samagra.nested_collapsing_view.test.HierarchyViewAdapter;
import com.samagra.nested_collapsing_view.test.HierarchyViewItem;
import com.samagra.nested_collapsing_view.test.ViewHolderSupplier;

import java.util.ArrayList;

public class SampleCategoryViewHolder extends AbstractBindedViewHolder {

    private View itemView;

    public SampleCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    @Override
    public void bindView(String key, String value) {
        Log.d("APP", "Binding View");
        TextView textView = itemView.findViewWithTag(key);
        if (textView != null)
            textView.setText(value);
    }

    @Override
    public ViewGroup getViewGroup() {
        return itemView.findViewById(android.R.id.content);
    }

    @Override
    public void addViewProperly(View view) {
        getViewGroup().addView(view);
    }

    @Override
    public void setupRecyclerView(ArrayList<HierarchyViewItem> viewItems, RecyclerView recyclerView, int depth) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        HierarchyViewAdapter<SampleItemViewHolder> itemAdapter = new HierarchyViewAdapter<>(viewItems, R.layout.layout_sample_item,
                new ViewHolderSupplier<SampleItemViewHolder>() {
                    @Override
                    public SampleItemViewHolder get(View view) {
                        return new SampleItemViewHolder(view);
                    }
                });
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.layout_sample_category;
    }
}

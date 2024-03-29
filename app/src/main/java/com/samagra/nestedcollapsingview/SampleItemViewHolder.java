package com.samagra.nestedcollapsingview;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samagra.nested_collapsing_view.AbstractBindedViewHolder;
import com.samagra.nested_collapsing_view.HierarchyViewItem;

import java.util.ArrayList;

public class SampleItemViewHolder extends AbstractBindedViewHolder {

    private View itemView;
    private View addedView = null;

    SampleItemViewHolder(@NonNull View itemView) {
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
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addedView = view;
        ((ViewGroup) itemView).addView(view, layoutParams);
    }

    @Override
    public void removeAddedView() {
        if (addedView != null) {
            ((ViewGroup) itemView).removeView(addedView);
            addedView = null;
        }
    }

    @Override
    public void setupRecyclerView(ArrayList<HierarchyViewItem> viewItems, RecyclerView recyclerView, int depth) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.layout_sample_item;
    }
}

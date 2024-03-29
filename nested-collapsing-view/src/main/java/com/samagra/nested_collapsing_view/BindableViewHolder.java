package com.samagra.nested_collapsing_view;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public interface BindableViewHolder {
    void bindView(String key, String value);

    ViewGroup getViewGroup();

    void addViewProperly(View view);

    void removeAddedView();

    void setupRecyclerView(ArrayList<HierarchyViewItem> viewItems, RecyclerView recyclerView, int depth);

    int getLayoutRes();
}

package com.samagra.nested_collapsing_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samagra.nested_collapsing_view.test.AbstractBindedViewHolder;
import com.samagra.nested_collapsing_view.test.HierarchyViewAdapter;
import com.samagra.nested_collapsing_view.test.HierarchyViewItem;
import com.samagra.nested_collapsing_view.test.ViewHolderSupplier;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NestedCollapsingView extends LinearLayout {

    private Context context;
    private int currentLevel = 0;
    private LinearLayout categoryHolder;

    public NestedCollapsingView(@NonNull Context context) {
        super(context);
        initView(context, null);
    }

    public NestedCollapsingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public NestedCollapsingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.context = context;
        loadPreferencesFromAttributes(attributeSet);
        LayoutInflater.from(context).inflate(R.layout.layout_collapsing_view, this);
        categoryHolder = findViewById(R.id.category_holder);
    }

    private void loadPreferencesFromAttributes(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.NestedCollapsingView);
            try {
                /*category_layout = typedArray.getResourceId(R.styleable.NestedCollapsingView_layout_category, R.layout.layout_sample_category);
                item_layout = typedArray.getResourceId(R.styleable.NestedCollapsingView_layout_item, R.layout.layout_sample_item);*/
                /*revealViewPercentageRight = typedArray.getFloat(R.styleable.HiddenLayoutView_revealPercentageViewRight, 0.2f);
                scaleHiddenView = typedArray.getBoolean(R.styleable.HiddenLayoutView_scaleHiddenView, false);
                animationType = typedArray.getString(R.styleable.HiddenLayoutView_animationEffect);
                maxMovementFactor = typedArray.getFloat(R.styleable.HiddenLayoutView_maxMovementFactorForSpring, 2);
                flingFriction = typedArray.getFloat(R.styleable.HiddenLayoutView_flingFriction, 0.8f);
                flingFrictionReverse = typedArray.getFloat(R.styleable.HiddenLayoutView_flingFrictionReverse, 0.001f);
                if (animationType == null || animationType.equals(""))
                    animationType = "2";*/
            } finally {
                typedArray.recycle();
            }
        }
    }

    public void initialize(JSONArray jsonArray, ViewHolderSupplier<? extends AbstractBindedViewHolder> viewHolderSupplier,
                           int rootLayoutRes) {
        ArrayList<HierarchyViewItem> hierarchyViewItems = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                hierarchyViewItems.add(new HierarchyViewItem(jsonObject.getJSONObject(HierarchyViewItem.KEYS_VIEW_MAPPING),
                        jsonObject.getJSONArray(HierarchyViewItem.KEYS_CHILDREN_ARRAY)));
            }
            inflateRootRecyclerView(hierarchyViewItems, rootLayoutRes, viewHolderSupplier);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void inflateRootRecyclerView(ArrayList<HierarchyViewItem> viewItems, int rootLayoutRes,
                                         ViewHolderSupplier<? extends AbstractBindedViewHolder> holderSupplier) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_template, categoryHolder, false);
        categoryHolder.addView(view);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        HierarchyViewAdapter<AbstractBindedViewHolder> adapter =
                new HierarchyViewAdapter<>(viewItems, rootLayoutRes, holderSupplier);
        recyclerView.setAdapter(adapter);
    }
}

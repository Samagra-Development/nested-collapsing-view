package com.samagra.nested_collapsing_view;


import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HierarchyViewItem {

    static final String KEYS_VIEW_MAPPING = "view_mapping";
    static final String KEYS_CHILDREN_ARRAY = "children";
    private HashMap<String, Object> viewMapping;
    private ArrayList<HierarchyViewItem> children;

    HierarchyViewItem(@NonNull JSONObject viewMapping, @NonNull JSONArray children) {
        try {
            this.viewMapping = convertJSONObjectToHashMap(viewMapping);
            populateChildrenArray(children);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Object> convertJSONObjectToHashMap(JSONObject viewMapping) throws JSONException {
        Iterator<String> iterator = viewMapping.keys();
        HashMap<String, Object> ans = new HashMap<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (!(viewMapping.get(key) instanceof JSONObject)) {
                ans.put(key, viewMapping.get(key));
            }
        }
        return ans;
    }

    private void populateChildrenArray(JSONArray children) throws JSONException {
        for (int i = 0; i < children.length(); i++) {
            JSONObject jsonObject = children.getJSONObject(i);
            if (jsonObject.has(KEYS_VIEW_MAPPING)) {
                HierarchyViewItem hierarchyViewItem = new HierarchyViewItem(
                        jsonObject.getJSONObject(KEYS_VIEW_MAPPING),
                        jsonObject.getJSONArray(KEYS_CHILDREN_ARRAY)
                );
                if (this.children == null)
                    this.children = new ArrayList<>();
                this.children.add(hierarchyViewItem);
            }
        }
    }

    HashMap<String, Object> getViewMapping() {
        return viewMapping;
    }

    ArrayList<HierarchyViewItem> getChildren() {
        return children;
    }
}

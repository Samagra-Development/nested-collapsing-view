package com.samagra.nestedcollapsingview;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.samagra.nested_collapsing_view.NestedCollapsingView;
import com.samagra.nested_collapsing_view.test.AbstractBindedViewHolder;
import com.samagra.nested_collapsing_view.test.ViewHolderSupplier;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private NestedCollapsingView nestedCollapsingView;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nestedCollapsingView = findViewById(R.id.test_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            readSampleJson();
        }
        nestedCollapsingView.initialize(jsonArray, new ViewHolderSupplier<AbstractBindedViewHolder>() {
            @Override
            public AbstractBindedViewHolder get(View view) {
                return new SampleCategoryViewHolder(view);
            }
        }, R.layout.layout_sample_category);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void readSampleJson() {
        try {
            InputStream inputStream = this.getResources().openRawResource(R.raw.sample);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            JSONObject jsonObject = new JSONObject(new String(buffer, StandardCharsets.UTF_8));
            Log.i("APP", jsonObject.toString());
            jsonArray = jsonObject.getJSONArray("data");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

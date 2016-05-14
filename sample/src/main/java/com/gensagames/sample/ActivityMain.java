package com.gensagames.sample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gensagames.sample.activities.ActivityPointMoving;
import com.gensagames.sample.activities.ActivityScaleCircle;
import com.gensagames.sample.activities.ActivitySimplePointMoving;
import com.gensagames.sample.adapter.GridAdapter;
import com.gensagames.sample.adapter.model.GridItem;


public class ActivityMain extends AppCompatActivity implements GridAdapter.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAdapters();
    }

    public void loadAdapters () {
        RecyclerView recycleView = (RecyclerView)findViewById(R.id.main_recycleview);
        recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        GridAdapter adapter = new GridAdapter(getApplicationContext());
        adapter.setOnItemClickListener(this);

        adapter.addItems(GridItem.getBaseItems(getApplicationContext()));
        recycleView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view) {
        String source = ((TextView)view.findViewById(R.id.grid_item_text)).getText().toString();
        if (TextUtils.equals(source, getString(R.string.grid_item_circle))) {
            startActivity(new Intent(getApplicationContext(), ActivityScaleCircle.class));
        }

        if (TextUtils.equals(source, getString(R.string.grid_item_point))) {
            startActivity(new Intent(getApplicationContext(), ActivityPointMoving.class));
        }

        if (TextUtils.equals(source, getString(R.string.grid_item_simple_point))) {
            startActivity(new Intent(getApplicationContext(), ActivitySimplePointMoving.class));
        }
    }




    public static void showToast (Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}

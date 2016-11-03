package com.gensagames.sample.adapter.model;

import android.content.Context;

import com.gensagames.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Genka on 09.05.2016.
 * GensaGames
 */
public class GridItem {
    private String name;
    private int resourceDrawable;

    public GridItem(String name, int resourceDrawable) {
        this.name = name;
        this.resourceDrawable = resourceDrawable;
    }

    public int getResourceDrawable() {
        return resourceDrawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<GridItem> getBaseItems (Context context) {
        List<GridItem> gridItems = new ArrayList<>();
        gridItems.add(new GridItem(context.getResources().getString(R.string.grid_item_circle),
                R.drawable.sample_image_logo3));
        gridItems.add(new GridItem(context.getResources().getString(R.string.grid_item_point),
                R.drawable.sample_image_logo2));
        gridItems.add(new GridItem(context.getResources().getString(R.string.grid_item_simple_point),
                R.drawable.sample_image_logo1));
        gridItems.add(new GridItem(context.getResources().getString(R.string.grid_item_center_motion),
                R.drawable.sample_image_logo4));
        return gridItems;
    }
}

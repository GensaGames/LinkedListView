package com.gensagames.sample.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gensagames.linkedlistview.LinkedListView;
import com.gensagames.linkedlistview.anim.ScaleCenterController;
import com.gensagames.linkedlistview.sample.ScaleCenterAdapter;
import com.gensagames.linkedlistview.sample.utils.DefaultSize;
import com.gensagames.sample.ActivityMain;
import com.gensagames.sample.R;
import com.thedeanda.lorem.LoremIpsum;

import java.util.Random;

/**
 * Created by Genka on 09.05.2016.
 * GensaGames
 */
public class ActivityScaleCircle extends Activity implements View.OnClickListener,
                LinkedListView.OnItemClickListener{


    private LinkedListView linkedListView;
    private ScaleCenterAdapter pagerAdapter;
    private ScaleCenterController animationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_linkedlistview);

        bindActivity();
        loadBaseStubs();
        setupCircleData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int sidePadding = linkedListView.getWidth() / 2
                - linkedListView.getPaddingStart() - linkedListView.getPaddingEnd();
        linkedListView.getMainViewHolder().setPadding(sidePadding, 0, sidePadding, 0);
    }

    /**
     * Testing work with LinkedListView, sample element initializing
     * and using for handling animation, clicks, etc.
     *
     */
    private void setupCircleData() {
        animationController = new ScaleCenterController(1.5, 0.5);
        animationController.setSelectableScroll(true);
        linkedListView.setAnimationController(animationController);

        pagerAdapter = new ScaleCenterAdapter(this);
        pagerAdapter.setOnItemClickListener(this);
        linkedListView.setViewPager(pagerAdapter);
    }

    /**
     * Testing work with LinkedListView, adding view to last index,
     * adding view in median index, removing view from index.
     *
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_fab_add_objects:
                ActivityMain.showToast(getApplicationContext(), "Adding last view");
                pagerAdapter.addSimpleView();
                break;
            case R.id.activity_fab_add_objects1:
                ActivityMain.showToast(getApplicationContext(), "Remove last view");
                pagerAdapter.deleteView(pagerAdapter.getObjectCount() - 1);
                break;
            case R.id.activity_fab_add_objects2:
                pagerAdapter.addSimpleView(2);
                break;
        }
    }

    @Override
    public void onItemClick(View view) {
        animationController.animateScrollTo(view, DefaultSize.SCROLL_ANIM_DURATION);
    }


    /**
     * -------------------------------------------
     *  Ignore! Base test generation stubs! For
     *  creating screenshots, and looking.
     *
     * -------------------------------------------
     */

    private void bindActivity() {
        linkedListView = (LinkedListView) findViewById(R.id.custom_pager_circle);
        findViewById(R.id.activity_fab_add_objects).setOnClickListener(this);
        findViewById(R.id.activity_fab_add_objects1).setOnClickListener(this);
        findViewById(R.id.activity_fab_add_objects2).setOnClickListener(this);
    }

    private void loadBaseStubs () {
        LinearLayout mainViewHolder = (LinearLayout) findViewById(R.id.layout_main_holder);
        LayoutInflater ltInflater = getLayoutInflater();

        for (int i = 0; i < 6; i++) {
            ViewGroup viewGroup = (ViewGroup) ltInflater.inflate(R.layout.base_list_item, null, false);
            TextView textViewName = (TextView)
                    viewGroup.findViewById(R.id.adapter_main_name);
            TextView textViewSecondaryName = (TextView)
                    viewGroup.findViewById(R.id.adapter_secondary_name);
            RatingBar ratingBar = (RatingBar)
                    viewGroup.findViewById(R.id.adapter_rating_bar);
            TextView textViewRating = (TextView)
                    viewGroup.findViewById(R.id.adapter_rating);
            TextView textViewDownloads = (TextView)
                    viewGroup.findViewById(R.id.adapter_downloads);

            textViewName.setText(LoremIpsum.getInstance().getTitle(200));
            textViewSecondaryName.setText(LoremIpsum.getInstance().getName());
            ratingBar.setRating((float) new Random().nextInt(5));
            textViewRating.setText(String.valueOf(new Random().nextInt(5)));
            textViewDownloads.setText(String.valueOf(new Random().nextInt(5000)));
            mainViewHolder.addView(viewGroup);
        }
        findViewById(R.id.activity_fab_add_objects).bringToFront();
        findViewById(R.id.activity_fab_add_objects1).bringToFront();
        findViewById(R.id.activity_fab_add_objects2).bringToFront();
    }
}

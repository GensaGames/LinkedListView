package com.gensagames.sample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gensagames.linkedlistview.LinkedListView;
import com.gensagames.linkedlistview.anim.CenterMotionController;
import com.gensagames.linkedlistview.sample.MotionCenterAdapter;
import com.gensagames.linkedlistview.sample.utils.DefaultSize;
import com.gensagames.sample.ActivityMain;
import com.gensagames.sample.R;
import com.thedeanda.lorem.LoremIpsum;

import java.util.Random;

public class ActivityCenterMotion extends AppCompatActivity implements View.OnClickListener,
        LinkedListView.OnItemClickListener {


    private LinkedListView linkedListView;
    private MotionCenterAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_motion);

        bindActivity();
        loadBaseStubs();
        setupPointData();
    }


    /**
     * Testing work with LinkedListView, sample element initializing
     * and using for handling animation, clicks, etc.
     *
     */

    CenterMotionController animationController;

    private void setupPointData() {
        linkedListView = (LinkedListView) findViewById(R.id.custom_pager_circle);
        animationController = new MotionCenterAdapter.AnimationController();
        pagerAdapter = new MotionCenterAdapter(this);
        pagerAdapter.setOnItemClickListener(this);

        linkedListView.setViewPager(pagerAdapter);
        linkedListView.setAnimationController(animationController);
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
                pagerAdapter.addSimpleView(pagerAdapter.getObjectCount() - 1);
                break;
            case R.id.activity_fab_add_objects2:
                pagerAdapter.deleteView(pagerAdapter.getObjectCount() - 1);
                break;
        }
    }

    @Override
    public void onItemClick(View view) {
        linkedListView.animatePagerChangeState(view, DefaultSize.SCROLL_ANIM_DURATION);
    }


    /**
     * Testing work with LinkedListView, adding view to last index,
     * adding view in median index, removing view from index.
     *
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
            ViewGroup viewGroup = (ViewGroup) ltInflater.inflate(R.layout.base_list_item_1, null, false);
            TextView text1 = (TextView)
                    viewGroup.findViewById(R.id.adapter_text1);
            TextView text2 = (TextView)
                    viewGroup.findViewById(R.id.adapter_text2);
            TextView text3 = (TextView)
                    viewGroup.findViewById(R.id.adapter_text3);
            TextView text4 = (TextView)
                    viewGroup.findViewById(R.id.adapter_text4);

            text1.setText(LoremIpsum.getInstance().getCity());
            text2.setText(LoremIpsum.getInstance().getCity());
            text3.setText(LoremIpsum.getInstance().getCity());
            text4.setText(LoremIpsum.getInstance().getCity());

            TextView textEstimate1 = (TextView)
                    viewGroup.findViewById(R.id.adapter_text_estimate1);
            TextView textEstimate2 = (TextView)
                    viewGroup.findViewById(R.id.adapter_text_estimate2);
            TextView textEstimate3 = (TextView)
                    viewGroup.findViewById(R.id.adapter_text_estimate3);
            TextView textEstimate4 = (TextView)
                    viewGroup.findViewById(R.id.adapter_text_estimate4);

            textEstimate1.setText(String.valueOf(new Random().nextInt(200)));
            textEstimate2.setText(String.valueOf(new Random().nextInt(200)));
            textEstimate3.setText(String.valueOf(new Random().nextInt(200)));
            textEstimate4.setText(String.valueOf(new Random().nextInt(200)));
            mainViewHolder.addView(viewGroup);
        }
        findViewById(R.id.activity_fab_add_objects).bringToFront();
        findViewById(R.id.activity_fab_add_objects1).bringToFront();
        findViewById(R.id.activity_fab_add_objects2).bringToFront();
    }

}

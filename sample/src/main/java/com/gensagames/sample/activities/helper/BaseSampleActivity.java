package com.gensagames.sample.activities.helper;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gensagames.linkedlistview.LinkedListView;
import com.gensagames.linkedlistview.anim.CenterMotionController;
import com.gensagames.linkedlistview.utils.DefaultSize;
import com.gensagames.sample.ActivityMain;
import com.gensagames.sample.R;
import com.gensagames.sample.adapter.helper.SampleLinkedAdapter;

/**
 * Created by GensaGames
 * GensaGames
 */

public abstract class BaseSampleActivity extends AppCompatActivity implements View.OnClickListener,
        LinkedListView.OnItemClickListener {


    protected LinearLayout mainLayoutSpace;
    protected LinkedListView linkedListView;
    protected SampleLinkedAdapter pagerAdapter;
    protected LinkedListView.AnimationController animationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_linkedlistview);

        bindActivity();
        setupLinkedListViewData();
        linkedListViewSetting();
    }


    @Override
    protected void onResume() {
        super.onResume();
        requestButtonsToFront();
    }

    /**
     * Testing work with LinkedListView, sample element initializing
     * and using for handling animation, clicks, etc.
     */
    public abstract void setupLinkedListViewData();

    /**
     * Testing work with LinkedListView, adding view to last index,
     * adding view in median index, removing view from index.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_fab_add_objects:
                ActivityMain.showToast(getApplicationContext(), "Adding last view");
                pagerAdapter.addSimpleView();
                break;
            case R.id.activity_fab_add_objects1:
                if (pagerAdapter.getObjectCount() > 0) {
                    ActivityMain.showToast(getApplicationContext(), "Remove last view");
                    pagerAdapter.deleteView(pagerAdapter.getObjectCount() - 1);
                }
                break;
        }
    }

    @Override
    public void onItemClick(View view) {
        animationController.animateScrollTo(view, DefaultSize.SCROLL_ANIM_DURATION);
    }


    private void bindActivity() {
        mainLayoutSpace = (LinearLayout) findViewById(R.id.layout_main_holder);
        linkedListView = (LinkedListView) findViewById(R.id.custom_pager_circle);
        findViewById(R.id.activity_fab_add_objects).setOnClickListener(this);
        findViewById(R.id.activity_fab_add_objects1).setOnClickListener(this);
    }

    private void linkedListViewSetting() {
        LayoutInflater ltInflater = getLayoutInflater();

        ViewGroup viewGroup = (ViewGroup) ltInflater.inflate(R.layout.object_config, mainLayoutSpace, false);
        ((TextView) viewGroup.findViewById(R.id.object_text_header_first))
                .setText(LinkedListView.class.getSimpleName());
        ((TextView) viewGroup.findViewById(R.id.object_text_description_first))
                .setText(getString(R.string.object_linked_mainholder_description));
        ((CheckBox) viewGroup.findViewById(R.id.object_checkbox_first))
                .setText(getString(R.string.object_linked_mainholder_checkbox));
        ((CheckBox) viewGroup.findViewById(R.id.object_checkbox_first))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int sidePadding = isChecked ? linkedListView.getWidth() / 2
                                - linkedListView.getPaddingStart() - linkedListView.getPaddingEnd() : 0;
                        linkedListView.getMainViewHolder().setPadding(sidePadding, 0, sidePadding, 0);
                        linkedListView.onScrollChanged();
                    }
                });
        mainLayoutSpace.addView(viewGroup);

    }


    protected void requestButtonsToFront() {
        findViewById(R.id.activity_fab_add_objects).bringToFront();
        findViewById(R.id.activity_fab_add_objects1).bringToFront();
    }
}

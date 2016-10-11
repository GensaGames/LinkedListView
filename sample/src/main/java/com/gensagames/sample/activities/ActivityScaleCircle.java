package com.gensagames.sample.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gensagames.linkedlistview.LinkedListView;
import com.gensagames.linkedlistview.anim.ScaleCenterController;
import com.gensagames.linkedlistview.sample.ScaleCenterAdapter;
import com.gensagames.linkedlistview.sample.utils.DefaultSize;
import com.gensagames.sample.ActivityMain;
import com.gensagames.sample.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by Genka on 09.05.2016.
 * GensaGames
 */
public class ActivityScaleCircle extends Activity implements View.OnClickListener,
                LinkedListView.OnItemClickListener{


    private LinearLayout mainLayoutSpace;
    private LinkedListView linkedListView;
    private ScaleCenterAdapter pagerAdapter;
    private ScaleCenterController animationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_linkedlistview);

        bindActivity();
        setupCircleData();


        linkedListViewSetting();
        scaleCenterControllerSetting();
        requestButtonsToFront();
    }

    /**
     * Testing work with LinkedListView, sample element initializing
     * and using for handling animation, clicks, etc.
     *
     */
    private void setupCircleData() {
        animationController = new ScaleCenterController(1.5, 0.5);
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

    private void scaleCenterControllerSetting() {
        LayoutInflater ltInflater = getLayoutInflater();
        ViewGroup viewGroup = (ViewGroup) ltInflater.inflate(R.layout.object_config, mainLayoutSpace, false);
        ViewGroup mainHolder = (LinearLayout) viewGroup.findViewById(R.id.object_main_layout);

        /**
         * ------------------------------------
         */
        ((TextView) viewGroup.findViewById(R.id.object_text_header_first))
                .setText(ScaleCenterController.class.getSimpleName());
        ((TextView) viewGroup.findViewById(R.id.object_text_description_first))
                .setText(getString(R.string.object_scalecenter_selectable_description));
        ((CheckBox) viewGroup.findViewById(R.id.object_checkbox_first))
                .setText(getString(R.string.object_scalecenter_selectable_checkbox));
        ((CheckBox) viewGroup.findViewById(R.id.object_checkbox_first))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        animationController.setSelectableScroll(isChecked);
                        linkedListView.onScrollChanged();
                    }
                });

        /**
         * -------------------- Center Max Scale----------------
         */

        ViewGroup seekBarGroup1 = (ViewGroup) ltInflater.inflate(R.layout.object_config_seekbar,
                mainLayoutSpace, false);
        ((TextView) seekBarGroup1.findViewById(R.id.object_seekbar_description))
                .setText(getString(R.string.object_scalecenter_seekbar_description));
        DiscreteSeekBar seekBar = (DiscreteSeekBar) seekBarGroup1.findViewById(R.id.object_seekbar);
        seekBar.setMin(100);
        seekBar.setMax(200);
        seekBar.setProgress(150);
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                animationController.setMaxCenterScale(((double) value / 100));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });
        mainHolder.addView(seekBarGroup1);


        mainLayoutSpace.addView(viewGroup);
    }

    private void requestButtonsToFront() {
        findViewById(R.id.activity_fab_add_objects).bringToFront();
        findViewById(R.id.activity_fab_add_objects1).bringToFront();
    }
}

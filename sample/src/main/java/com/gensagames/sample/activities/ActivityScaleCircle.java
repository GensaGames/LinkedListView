package com.gensagames.sample.activities;

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
import com.gensagames.linkedlistview.utils.DefaultSize;
import com.gensagames.sample.ActivityMain;
import com.gensagames.sample.R;
import com.gensagames.sample.adapter.sample.ScaleCenterAdapter;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by Genka on 09.05.2016.
 * GensaGames
 */
public class ActivityScaleCircle extends BaseSampleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scaleCenterControllerSetting();
    }

    @Override
    public void setupLinkedListViewData() {
        animationController = new ScaleCenterController(1.0);
        linkedListView.setAnimationController(animationController);

        pagerAdapter = new ScaleCenterAdapter(this);
        pagerAdapter.setOnItemClickListener(this);
        linkedListView.setViewPager(pagerAdapter);
    }

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
                ActivityMain.showToast(getApplicationContext(), "Remove last view");
                pagerAdapter.deleteView(pagerAdapter.getObjectCount() - 1);
                break;
        }
    }

    @Override
    public void onItemClick(View view) {
        animationController.animateScrollTo(view, DefaultSize.SCROLL_ANIM_DURATION);
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

    /**
     * ----- Creating all options to handle with AnimationController -----
     */
    private void scaleCenterControllerSetting() {
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.object_config, mainLayoutSpace, false);
        ViewGroup mainHolder = (LinearLayout) viewGroup.findViewById(R.id.object_main_layout);

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
                        ((ScaleCenterController) animationController).setSelectableScroll(isChecked);
                        linkedListView.onScrollChanged();
                    }
                });


        optionSetMaxScale(mainHolder);
        optionMinSideScale(mainHolder);
        mainLayoutSpace.addView(viewGroup);
    }

    /**
     * ------- ScaleCenterController - Center Max Scale-----------
     */
    private void optionSetMaxScale(ViewGroup parentView) {
        ViewGroup seekBarGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.object_config_seekbar, mainLayoutSpace, false);
        ((TextView) seekBarGroup.findViewById(R.id.object_seekbar_description))
                .setText(getString(R.string.object_scalecenter_seekbar_description1));
        DiscreteSeekBar seekBar = (DiscreteSeekBar) seekBarGroup.findViewById(R.id.object_seekbar);
        seekBar.setMin(50);
        seekBar.setMax(150);
        seekBar.setProgress(100);
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                ((ScaleCenterController) animationController).setMaxCenterScale(((double) value / 100));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });
        parentView.addView(seekBarGroup);
    }

    /**
     * ------ ScaleCenterController - Min Side Delta ----------------
     */
    private void optionMinSideScale(ViewGroup parentView) {
        ViewGroup seekBarGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.object_config_seekbar, mainLayoutSpace, false);
        ((TextView) seekBarGroup.findViewById(R.id.object_seekbar_description))
                .setText(getString(R.string.object_scalecenter_seekbar_description2));
        DiscreteSeekBar seekBar = (DiscreteSeekBar) seekBarGroup.findViewById(R.id.object_seekbar);
        seekBar.setMin(100);
        seekBar.setMax(350);
        seekBar.setProgress(150);
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                ((ScaleCenterController) animationController).setDeltaScaleView(((double) value / 100));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });
        parentView.addView(seekBarGroup);
    }
}

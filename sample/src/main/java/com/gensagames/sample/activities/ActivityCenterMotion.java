package com.gensagames.sample.activities;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gensagames.linkedlistview.anim.CenterMotionController;
import com.gensagames.linkedlistview.anim.ScaleCenterController;
import com.gensagames.sample.R;
import com.gensagames.sample.activities.helper.BaseSampleActivity;
import com.gensagames.sample.adapter.sample.MotionCenterAdapter;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class ActivityCenterMotion extends BaseSampleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        motionCenterControllerSetting();
    }

    @Override
    public void setupLinkedListViewData() {
        animationController = new MotionCenterAdapter.AnimationController();
        pagerAdapter = new MotionCenterAdapter(this);
        pagerAdapter.setOnItemClickListener(this);

        linkedListView.setViewPager(pagerAdapter);
        linkedListView.setAnimationController(animationController);
    }

    /**
     * ----- Creating all options to handle with AnimationController -----
     */
    private void motionCenterControllerSetting() {
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.object_config, mainLayoutSpace, false);
        ViewGroup mainHolder = (LinearLayout) viewGroup.findViewById(R.id.object_main_layout);

        ((TextView) viewGroup.findViewById(R.id.object_text_header_first))
                .setText(CenterMotionController.class.getSimpleName());
        ((TextView) viewGroup.findViewById(R.id.object_text_description_first))
                .setText(getString(R.string.object_center_motion_description1));
        ((CheckBox) viewGroup.findViewById(R.id.object_checkbox_first))
                .setText(getString(R.string.object_center_motion_checkbox1));
        ((CheckBox) viewGroup.findViewById(R.id.object_checkbox_first))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ((CenterMotionController) animationController).setSelectableScroll(isChecked);
                        linkedListView.onScrollChanged();
                    }
                });

        optionSetMaxMargin(mainHolder);
        optionSetLeftOverlap(mainHolder);
        optionSetRightOverlap(mainHolder);
        mainLayoutSpace.addView(viewGroup);
    }
    /**
     * ------- ScaleCenterController - Max overlap-----------
     */
    private void optionSetMaxMargin(ViewGroup parentView) {
        ViewGroup seekBarGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.object_config_seekbar, mainLayoutSpace, false);
        ((TextView) seekBarGroup.findViewById(R.id.object_seekbar_description))
                .setText(R.string.object_center_motion_margin);
        DiscreteSeekBar seekBar = (DiscreteSeekBar) seekBarGroup.findViewById(R.id.object_seekbar);
        seekBar.setMin(0);
        seekBar.setMax(160);
        seekBar.setProgress(80);
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                ((CenterMotionController) animationController).setMaxTranslationX(value);
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
     * ------- ScaleCenterController - Right side -----------
     */
    private void optionSetLeftOverlap(ViewGroup parentView) {
        ViewGroup seekBarGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.object_config_seekbar, mainLayoutSpace, false);
        ((TextView) seekBarGroup.findViewById(R.id.object_seekbar_description))
                .setText(R.string.object_center_motion_left_overlap);
        DiscreteSeekBar seekBar = (DiscreteSeekBar) seekBarGroup.findViewById(R.id.object_seekbar);
        seekBar.setMin(-100);
        seekBar.setMax(100);
        seekBar.setProgress(0);
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                ((CenterMotionController) animationController).setOptLeftOverlap(value);
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
     * ------- ScaleCenterController - Left side-----------
     */
    private void optionSetRightOverlap(ViewGroup parentView) {
        ViewGroup seekBarGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.object_config_seekbar, mainLayoutSpace, false);
        ((TextView) seekBarGroup.findViewById(R.id.object_seekbar_description))
                .setText(R.string.object_center_motion_right_overlap);
        DiscreteSeekBar seekBar = (DiscreteSeekBar) seekBarGroup.findViewById(R.id.object_seekbar);
        seekBar.setMin(-100);
        seekBar.setMax(100);
        seekBar.setProgress(0);
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                ((CenterMotionController) animationController).setOptRightOverlap(value);
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

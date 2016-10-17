package com.gensagames.sample.activities;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gensagames.linkedlistview.anim.ScaleCenterController;
import com.gensagames.sample.R;
import com.gensagames.sample.activities.helper.BaseSampleActivity;
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

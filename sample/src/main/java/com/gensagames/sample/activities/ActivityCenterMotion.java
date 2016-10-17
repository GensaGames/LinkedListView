package com.gensagames.sample.activities;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.gensagames.linkedlistview.anim.CenterMotionController;
import com.gensagames.sample.R;
import com.gensagames.sample.activities.helper.BaseSampleActivity;
import com.gensagames.sample.adapter.sample.MotionCenterAdapter;

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

        mainLayoutSpace.addView(viewGroup);
    }


}

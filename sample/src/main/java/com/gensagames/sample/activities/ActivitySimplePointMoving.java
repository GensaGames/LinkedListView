package com.gensagames.sample.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gensagames.linkedlistview.anim.PointMovingController;
import com.gensagames.sample.R;
import com.gensagames.sample.activities.helper.BaseSampleActivity;
import com.gensagames.sample.adapter.sample.SimplePointAdapter;

/**
 * Created by Genka on 10.05.2016.
 * GensaGames
 */
public class ActivitySimplePointMoving extends BaseSampleActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scaleCenterControllerSetting();
    }


    @Override
    public void setupLinkedListViewData() {
        animationController = new SimplePointAdapter.AnimationController();
        pagerAdapter = new SimplePointAdapter(this);
        pagerAdapter.setOnItemClickListener(this);

        linkedListView.setAdapter(pagerAdapter);
        linkedListView.setAnimationController(animationController);
    }

    /**
     * ----- Creating all options to handle with AnimationController -----
     */
    private void scaleCenterControllerSetting() {
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.object_config, mainLayoutSpace, false);

        String currentText = this.getClass().getSimpleName()
                .equals(ActivitySimplePointMoving.class.getSimpleName()) ?
                getString(R.string.object_pointvoing_sample_description1) :
                getString(R.string.object_pointvoing_sample_description2);

        ((TextView) viewGroup.findViewById(R.id.object_text_header_first))
                .setText(PointMovingController.class.getSimpleName());
        ((TextView) viewGroup.findViewById(R.id.object_text_description_first))
                .setText(currentText);
        viewGroup.findViewById(R.id.object_checkbox_first)
                .setVisibility(View.GONE);

        mainLayoutSpace.addView(viewGroup);
    }

}

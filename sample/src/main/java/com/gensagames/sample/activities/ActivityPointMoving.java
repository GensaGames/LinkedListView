package com.gensagames.sample.activities;

import com.gensagames.sample.adapter.sample.PointMovingAdapter;

/**
 * Created by Genka on 10.05.2016.
 * GensaGames
 */
public class ActivityPointMoving extends ActivitySimplePointMoving {

    @Override
    public void setupLinkedListViewData() {
        animationController = new PointMovingAdapter.AnimationController();
        pagerAdapter = new PointMovingAdapter(this);
        pagerAdapter.setOnItemClickListener(this);

        linkedListView.setAdapter(pagerAdapter);
        linkedListView.setAnimationController(animationController);
    }
}

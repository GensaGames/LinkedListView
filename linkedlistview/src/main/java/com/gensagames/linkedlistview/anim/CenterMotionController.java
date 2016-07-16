package com.gensagames.linkedlistview.anim;


import android.view.ViewGroup;

/**
 * Created by Genka on 16.07.2016.
 * GensaGames
 */
public abstract class CenterMotionController extends ScaleCenterController {

    public CenterMotionController() {
    }

    public CenterMotionController(double maxCenterScale, double minSideScale) {
        super(maxCenterScale, minSideScale);
    }

    public CenterMotionController(double maxCenterScale, double minSideScale, double deltaScaleView) {
        super(maxCenterScale, minSideScale, deltaScaleView);
    }

    @Override
    public void onScrollAction() {
        int firstVisibleView = getFirstVisiblePosition();
        int lastVisibleView = getLastVisiblePosition();

        for (int i = firstVisibleView; i <= lastVisibleView; i++) {
            ViewGroup mainView = (ViewGroup) getMainViewHolder().getChildAt(i);
            if (mainView == null) {
                break;
            }

        }
    }
}

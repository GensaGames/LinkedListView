package com.gensagames.linkedlistview.anim;


import android.view.View;
import android.view.ViewGroup;

import com.gensagames.linkedlistview.LinkedListView;

/**
 * Created by Genka on 16.07.2016.
 * GensaGames
 */
public abstract class CenterMotionController extends LinkedListView.AnimationController {

    /**
     * Max overlap on other views.
     * Should be configured on every usage!
     */
    private float maxTranslationX = 80;
    private float optLeftOverlap = 0;
    private float optRightOverlap = 0;

    /**
     * Configurable from methods values
     */
    private int selectableScrollDuration = 300;
    private boolean isSelectableScroll;

    public CenterMotionController() {
    }


    @Override
    public void onScrollStart() {

    }

    @Override
    public void onScrollStop() {
        if (isSelectableScroll) {
            ViewGroup mainScrollView = getMainViewHolder();
            if (mainScrollView == null) {
                return;
            }
            animateScrollTo(mainScrollView.getChildAt(getCenterViewIndex()), selectableScrollDuration);
        }
    }

    @Override
    public void onScrollAction() {
        ViewGroup mainScrollView = getMainViewHolder();
        if (mainScrollView == null) {
            return;
        }

        for (int i = 0; i < mainScrollView.getChildCount(); i++) {
            ViewGroup mainView = (ViewGroup) mainScrollView.getChildAt(i);
            if (mainView == null) {
                break;
            }

            mainView.setTranslationX(updateSideTranslation(mainView));
            updateCenterTranslation(mainView);
        }

    }

    private void updateCenterTranslation(View mainView) {
        if (getScrollToLeftSeparate(mainView) > 0 && getScrollToRightSeparate(mainView) < 0) {
            int totalScrollToCenter = getTotalScrollToCenter(mainView);
            int signumOfScroll = (int) Math.signum(totalScrollToCenter);
            int scrollViewHalf = getScrollViewWidth() / 2;

            float sideOverlap = signumOfScroll < 0 ? scrollViewHalf - getLeftOverlapFromCenter()
                    :  getRightOverlapFromCenter();
            mainView.setTranslationX(getUpdatedTranslation(totalScrollToCenter,
                    (maxTranslationX * signumOfScroll), (int) sideOverlap));
        }
    }

    public float updateSideTranslation(View mainView) {
        int leftSeparate = getScrollToLeftSeparate(mainView);
        int rightSeparate = getScrollToRightSeparate(mainView);
        float updated = 0;

        if (leftSeparate <= 0) {
            updated = -1 * maxTranslationX + getUpdatedTranslation(leftSeparate, maxTranslationX,
                    (int) getLeftOverlapFromCenter());
        }
        if (rightSeparate >= 0) {
            updated = maxTranslationX - getUpdatedTranslation(rightSeparate, maxTranslationX,
                    (int) getRightOverlapFromCenter());
        }
        return updated;
    }


    public int getScrollToLeftSeparate(View viewOnLayout) {
        int scrollToCenter = getScroll() + (int) getLeftOverlapFromCenter ();
        int scrollToView = getScrollToView(viewOnLayout) + (viewOnLayout.getWidth() / 2);
        return scrollToView - scrollToCenter;
    }

    public int getScrollToRightSeparate(View viewOnLayout) {
        int includingScrollViewSide = getScrollViewWidth() / 2;
        int scrollToSeparate = getScroll() + includingScrollViewSide + (int) getRightOverlapFromCenter();
        int scrollToView = getScrollToView(viewOnLayout) + (viewOnLayout.getWidth() / 2);
        return scrollToView - scrollToSeparate;
    }

    private float getUpdatedTranslation(int separateDiff, float currentTrans, int sideOverlap) {
        return (Math.abs((float) separateDiff / sideOverlap) * currentTrans);
    }

    /**
     * Getter and Setter
     */

    public float getLeftOverlapFromCenter () {
        return getScrollViewWidth() / 4 + optLeftOverlap;
    }

    public float getRightOverlapFromCenter () {
        return getScrollViewWidth() / 4 + optRightOverlap;
    }

    public void setMaxTranslationX (float maxTranslationX) {
        this.maxTranslationX = maxTranslationX;
    }

    public void setOptLeftOverlap(float optLeftOverlap) {
        this.optLeftOverlap = optLeftOverlap;
    }

    public void setOptRightOverlap(float optRightOverlap) {
        this.optRightOverlap = optRightOverlap;
    }

    /**
     * Selectable scroll action
     */


    public void setSelectableScroll(boolean isSelectableScroll) {
        this.isSelectableScroll = isSelectableScroll;
        onScrollAction();
    }

    public void setSelectableScrollDuration(int selectableScrollDuration) {
        this.selectableScrollDuration = selectableScrollDuration;
    }
}

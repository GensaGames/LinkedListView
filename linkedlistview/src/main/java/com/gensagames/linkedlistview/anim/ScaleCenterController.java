package com.gensagames.linkedlistview.anim;

import android.view.ViewGroup;

import com.gensagames.linkedlistview.LinkedListView;


/**
 * Created by Genka on 30.03.2016.
 * GensaGames
 */
public class ScaleCenterController extends LinkedListView.AnimationController {

    /**
     * Max scale which will be
     * scrolling to center (Percent)
     */
    private double maxCenterScale;
    /**
     * Min scale from max left scroll
     * value and right sides (Percent)
     */
    private double minSideScale;
    /**
     * Delta between two previous
     * values when scrolling (Percent)
     */
    private double deltaScaleView;


    /**
     * Configurable from methods values
     */
    private int selectableScrollDuration = 300;
    private boolean isSelectableScroll;

    /**
     * Base constructor for
     * examples
     */
    public ScaleCenterController() {
        maxCenterScale = 1.0;
        minSideScale = 1.0;
        deltaScaleView = 2.0;
    }

    /**
     * Parameters constructor:
     *
     * @param maxCenterScale - center max percent scale
     * @param minSideScale - side min percent scale
     */
    public ScaleCenterController(double maxCenterScale, double minSideScale) {
        this.maxCenterScale = maxCenterScale;
        this.minSideScale = minSideScale;
        deltaScaleView = 2.0;
    }

    /**
     * * Parameters constructor:
     *
     * @param maxCenterScale - center max percent scale
     * @param minSideScale - side min percent scale
     * @param deltaScaleView - delta between previous values
     */
    public ScaleCenterController(double maxCenterScale, double minSideScale, double deltaScaleView) {
        this.maxCenterScale = maxCenterScale;
        this.minSideScale = minSideScale;
        this.deltaScaleView = deltaScaleView;
    }

    /**
     * Update scrolling animation
     * and change scale parameter
     */

    @Override
    public void onScrollAction() {
        int firstVisibleView = getFirstVisiblePosition();
        int lastVisibleView = getLastVisiblePosition();
        int scrollViewWidth = getScrollViewWidth();

        for (int i = firstVisibleView; i <= lastVisibleView; i++) {
            ViewGroup mainView = (ViewGroup) getMainViewHolder().getChildAt(i);
            if (mainView == null) {
                break;
            }

            float newScale = (float) (getScrollToCenterRate(getTotalScrollToCenter(mainView), scrollViewWidth));
            mainView.setScaleX(newScale);
            mainView.setScaleY(newScale);
        }
    }

    @Override
    public void onScrollStart() {

    }

    @Override
    public void onScrollStop() {
        if (isSelectableScroll) {
            animateScrollTo(getMainViewHolder().getChildAt(getCenterViewIndex()), selectableScrollDuration);
        }
    }

    public void setSelectableScroll(boolean isSelectableScroll) {
        this.isSelectableScroll = isSelectableScroll;
        onScrollAction();
    }

    public void setSelectableScrollDuration(int selectableScrollDuration) {
        this.selectableScrollDuration = selectableScrollDuration;
    }

    public void setMaxCenterScale(double maxCenterScale) {
        this.maxCenterScale = maxCenterScale;
        onScrollAction();
    }

    public void setMinSideScale(double minSideScale) {
        this.minSideScale = minSideScale;
        onScrollAction();
    }

    public void setDeltaScaleView(double deltaScaleView) {
        this.deltaScaleView = deltaScaleView;
        onScrollAction();
    }

    /**
     * Get updated percent scale from
     * center difference
     *
     * @param difference - int difference to center
     * @return - invoke all values, to get
     * updated percent value of base view scale.
     */
    public double getScrollToCenterRate(int difference, int scrollViewWidth) {
        double pcDifference = (double) Math.abs(difference) / (scrollViewWidth / deltaScaleView);
        return (maxCenterScale - pcDifference) + minSideScale;
    }




}

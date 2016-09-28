package com.gensagames.linkedlistview.anim;

import android.view.View;
import android.view.ViewGroup;

import com.gensagames.linkedlistview.LinkedListView;


/**
 * Created by Genka on 30.03.2016.
 * GensaGames
 */
public abstract class ScaleCenterController extends LinkedListView.AnimationController {

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

        for (int i = firstVisibleView; i <= lastVisibleView; i++) {
            ViewGroup mainView = (ViewGroup) getMainViewHolder().getChildAt(i);
            if (mainView == null) {
                break;
            }

            float newScale = (float) (getScrollToCenterRate(getTotalScrollToCenter(mainView)));
            updateScaleWhileMoving(mainView, newScale);
        }
    }

    public void updateScaleWhileMoving(ViewGroup mainView, float updatedScale) {
        getFocusView(mainView).setScaleX(updatedScale);
        getFocusView(mainView).setScaleY(updatedScale);
    }

    public abstract View getFocusView (ViewGroup mainView);

    /**
     * Get updated percent scale from
     * center difference
     *
     * @param intDifference - int difference to center
     * @return - invoke all values, to get
     * updated percent value of base view scale.
     */
    public double getScrollToCenterRate(int intDifference) {
        int difference =  Math.abs(intDifference);
        double pcDifference = (double) difference / (getScrollViewWidth() / deltaScaleView);
        return (maxCenterScale - pcDifference) + minSideScale;
    }




}

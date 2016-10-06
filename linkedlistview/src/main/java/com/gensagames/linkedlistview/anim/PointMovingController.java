package com.gensagames.linkedlistview.anim;

import android.view.Gravity;
import android.view.ViewGroup;

import com.gensagames.linkedlistview.LinkedListView;


/**
 * Created by Genka on 13.04.2016.
 * GensaGames
 */
public abstract class PointMovingController extends LinkedListView.AnimationController {


    @Override
    public void onScrollAction() {
        updateSideViewsX();
        updateCenterViewsX();
    }


    /**
     * Method for getting delta, when calculation
     * translate X position in list.
     *
     * @param viewGroup main view on list
     * @param gravity - gravity, only start or end of list
     * @return - delta of point translate X
     */
    public int getDeltaPointView (ViewGroup viewGroup, int gravity) {
        int delta = (viewGroup.getWidth() / 2) - (viewGroup.getWidth() / 2) - 1;

        if (gravity == Gravity.START) {
            return -delta;
        }
        if (gravity == Gravity.END) {
            return delta;
        }
        return 0;
    }

    /**
     * MAIN!
     * Function for updating side views.
     * That view most moving in their translate X
     * max and min position, for creating freezing
     * effect.
     *
     */
    public void updateSideViewsX() {
        ViewGroup firstViewParent = (ViewGroup) getMainViewHolder()
                .getChildAt(getFirstVisiblePosition());
        ViewGroup lastViewParent = (ViewGroup) getMainViewHolder()
                .getChildAt(getLastVisiblePosition());

        if (firstViewParent == null || lastViewParent == null) {
            return;
        }

        int mainScrollOffset = getScroll() - getScrollToView(firstViewParent);
        int croppedRight = croppedSideView();

        /**
         * Left! Invoke values to get
         * correct translated X
         */
        int translateStartX = mainScrollOffset
                + getDeltaPointView(firstViewParent, Gravity.START);
        updateViewsTranslatedX(firstViewParent, translateStartX);
        animateMovingOut(firstViewParent);
        /**
         * Right! Invoke values to get
         * correct translated X. In this case
         * we most to check cropped side!
         */
        int translateEndX =  mainScrollOffset - croppedRight
                + getDeltaPointView(lastViewParent, Gravity.END);

        if (mainScrollOffset > croppedRight) {
            translateEndX = translateEndX - lastViewParent.getWidth();
        }
        updateViewsTranslatedX(lastViewParent, translateEndX);
        animateMovingOut(lastViewParent);
    }


    /**
     * MAIN!
     * Other main function, for updating
     * translate X in all other visible views.
     * They most moving on X every onScroll action
     * and moving to center (int left scroll to absolute center)
     * and from center (in right scroll to absolute center).
     * For making overlaying with side view!
     */
    public void updateCenterViewsX() {

        for (int i = getFirstVisiblePosition() + 1; i < getLastVisiblePosition(); i++) {
            ViewGroup parentView = (ViewGroup)getMainViewHolder().getChildAt(i);

            int scrolledToCenter = getScrollToCenter(parentView);
            float translated = calculateScrollTranslated(scrolledToCenter);

            updateViewsTranslatedX(parentView, (int)translated);
            animateMovingIn(parentView);
        }
    }

    /**
     * If you will adding views, some view will
     * cropped in right side. Need for other calculation!
     *
     * @return int value of cropped view
     */
    public final int croppedSideView () {
        ViewGroup mainViewHolder = getMainViewHolder();
        int croppedSide = 0;
        int scrollViewWidth = getScrollViewWidth();
        for (int i = 0; i < mainViewHolder.getChildCount(); i ++) {
            croppedSide+= mainViewHolder.getChildAt(i).getWidth();
            if (croppedSide > scrollViewWidth) {
                croppedSide = croppedSide - scrollViewWidth;
                break;
            }
        }
        return croppedSide;
    }

    /**
     * Invoke values for getting updated translate X
     * position on every scroll action! For all visible views,
     * except first and last.
     * TODO(Fix): Update some location!
     *
     * @param centerDifference - difference to center
     * @return - new translated X value
     */

    public float calculateScrollTranslated(int centerDifference) {
        ViewGroup mainView = (ViewGroup) getMainViewHolder().getChildAt(0);
        int updatedCenterDifference = centerDifference + mainView.getWidth() / 2;

        float maxAvailableTranslate = (mainView.getWidth() / 2) - mainView.getWidth() / 2;
        float maxDifferenceToCenter = getScrollViewWidth() / 2 - (mainView.getWidth() / 2)
                - mainView.getWidth();

        double scrollPercent = (double) updatedCenterDifference / maxDifferenceToCenter;
        scrollPercent = scrollPercent > 1.0 ? 1.0 : scrollPercent;
        scrollPercent = scrollPercent < -1.0 ? -1.0 : scrollPercent;
        return (float)(maxAvailableTranslate * scrollPercent);
    }


    public void updateViewsTranslatedX (ViewGroup mainView, int newTranslationX) {
        mainView.setTranslationX(newTranslationX);
    }

    public abstract void animateMovingIn(ViewGroup mainView);
    public abstract void animateMovingOut(ViewGroup mainView);


}

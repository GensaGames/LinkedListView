package com.gensagames.linkedlistview.anim;


import android.view.View;
import android.view.ViewGroup;

import com.gensagames.linkedlistview.LinkedListView;

/**
 * Created by Genka on 16.07.2016.
 * GensaGames
 */
public abstract class CenterMotionController extends LinkedListView.AnimationController {

    private float maxTranslationX = 82;

    public CenterMotionController() {
    }

    @Override
    public void onScrollAction() {
        for (int i = 0; i < getMainViewHolder().getChildCount(); i++) {
            ViewGroup mainView = (ViewGroup) getMainViewHolder().getChildAt(i);
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
            mainView.setTranslationX(getUpdatedTranslation(totalScrollToCenter,
                    (maxTranslationX * Math.signum(totalScrollToCenter))));
        }
    }

    public float updateSideTranslation(View mainView) {
        int leftSeparate = getScrollToLeftSeparate(mainView);
        int rightSeparate = getScrollToRightSeparate(mainView);
        int maxSide = getScrollViewWidth() / 4;
        float updated = 0;

        if (leftSeparate <= 0 && Math.abs(leftSeparate) <= maxSide) {
            updated = -1 * maxTranslationX + getUpdatedTranslation(leftSeparate, maxTranslationX);
        }
        if (rightSeparate >= 0 && Math.abs(rightSeparate) <= maxSide) {
            updated = maxTranslationX - getUpdatedTranslation(rightSeparate, maxTranslationX);
        }
        return updated;
    }


    public int getScrollToLeftSeparate(View viewOnLayout) {
        int scrollToCenter = getScroll() + getScrollViewWidth() / 4;
        int scrollToView = getScrollToView(viewOnLayout) + (viewOnLayout.getWidth() / 2);
        return scrollToView - scrollToCenter;
    }

    public int getScrollToRightSeparate(View viewOnLayout) {
        int scrollToCenter = getScroll() + ((getScrollViewWidth() / 4) * 3);
        int scrollToView = getScrollToView(viewOnLayout) + (viewOnLayout.getWidth() / 2);
        return scrollToView - scrollToCenter;
    }

    public float getUpdatedTranslation(int separateDiff, float currentTrans) {
        return (Math.abs((float) separateDiff / (getScrollViewWidth() / 4)) * currentTrans);
    }


}

package com.gensagames.linkedlistview.anim;

import android.view.ViewGroup;

import com.gensagames.linkedlistview.LinkedListView;


/**
 * Created by Genka on 02.05.2016.
 * GensaGames
 */
public abstract class SideMovingController extends LinkedListView.AnimationController {


    public SideMovingController () {
        throw new UnsupportedOperationException("This controller not implemented yet!");
    }
    public ViewGroup getMainListView () {
        return null;
    }

    @Override
    public void onScrollAction() {

    }

    public void updateTranslated () {
        ViewGroup mainViewHolder = getMainViewHolder();
        int firstV = getFirstVisiblePosition();
        int lastV = getLastVisiblePosition();

        ViewGroup viewOnList = getMainListView();

        viewOnList.setTranslationX(getScroll() - getScrollToView(viewOnList));

        int viewScroll = 0;
        for (int i = mainViewHolder.getChildCount(); i > lastV; i--) {
            if (mainViewHolder.getChildAt(i) != null)
                viewScroll+= mainViewHolder.getChildAt(i).getWidth();
        }

        int scroll = getMainViewHolder().getWidth() - getScrollViewWidth() - getScroll();
        viewOnList.setTranslationX(scroll - viewScroll);

        for (int i = firstV + 1; i < lastV; i++) {
            ViewGroup parentView = (ViewGroup)getMainViewHolder().getChildAt(i);
            parentView.setTranslationX(0);
            parentView.setTranslationX(0);
        }
    }
}

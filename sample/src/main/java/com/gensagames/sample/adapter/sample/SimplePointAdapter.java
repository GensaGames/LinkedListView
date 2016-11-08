package com.gensagames.sample.adapter.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gensagames.linkedlistview.anim.PointMovingController;
import com.gensagames.sample.adapter.helper.SampleLinkedAdapter;
import com.gensagames.sample.util.BaseDrawable;

import java.util.LinkedList;

/**
 * Created by Genka on 10.05.2016.
 * GensaGames
 */
public class SimplePointAdapter extends SampleLinkedAdapter {

    private Context mainContext;
    private LinkedList<View> mainViewList;

    public SimplePointAdapter(Context mainContext) {
        this.mainContext = mainContext;
        mainViewList = new LinkedList<>();
    }

    @Override
    public void addSimpleView() {
        ViewGroup mainView = BaseDrawable.getNumericPoint(mainContext);
        mainViewList.add(mainView);
        notifyDataSetChanged();
    }

    @Override
    public void deleteView(int index) {
        mainViewList.remove(index);
        notifyDataSetChanged();
    }


    @Override
    public View getObjectView(int position, ViewGroup parentView) {
        return mainViewList.get(position);
    }

    @Override
    public int getObjectCount() {
        return mainViewList.size();
    }

    @Override
    public void bindView(int position) {

    }

    /**
     * --------------------------------------------
     * Testing another element - AnimationController,
     * that extend from more specific controller
     * -----------------------------------------
     */


    public static class AnimationController extends PointMovingController {

        @Override
        public void updateViewsTranslatedX(ViewGroup mainView, int newTranslationX) {
            super.updateViewsTranslatedX(mainView, newTranslationX);

            if (getFocusViewText(mainView).getText().toString().equals("")) {
                getFocusViewText(mainView).setText(String.valueOf(getMainViewHolder()
                        .indexOfChild(mainView) + 10));
            }
        }

        @Override
        public void animateMovingIn(final ViewGroup mainView) {

        }

        @Override
        public void animateMovingOut(ViewGroup mainView) {

        }

        @Override
        public void onScrollAction() {
            super.onScrollAction();
            updateClipOutside();
        }

        private void updateClipOutside() {
            int firstVisible = getFirstVisiblePosition();
            int lastVisible = getLastVisiblePosition();
            for (int i = firstVisible; i <= lastVisible; i++) {
                ViewGroup viewGroup = (ViewGroup) getMainViewHolder().getChildAt(i);
                if (viewGroup == null) {
                    continue;
                }
                viewGroup.setVisibility(View.VISIBLE);
            }
            ViewGroup viewGroupPre = (ViewGroup) getMainViewHolder().getChildAt(firstVisible - 1);
            if (viewGroupPre != null) {
                viewGroupPre.setVisibility(View.INVISIBLE);
            }
            ViewGroup viewGroupPost = (ViewGroup) getMainViewHolder().getChildAt(lastVisible + 1);
            if (viewGroupPost != null) {
                viewGroupPost.setVisibility(View.INVISIBLE);
            }
        }

        public TextView getFocusViewText(ViewGroup parentView) {
            for (int i = 0; i < parentView.getChildCount(); i++) {
                if (parentView.getChildAt(i) instanceof TextView) {
                    return (TextView) parentView.getChildAt(i);
                }
            }
            return null;
        }

        @Override
        public void onScrollStop() {

        }

        @Override
        public void onScrollStart() {

        }
    }

}

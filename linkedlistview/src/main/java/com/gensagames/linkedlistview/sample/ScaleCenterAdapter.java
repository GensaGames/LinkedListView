package com.gensagames.linkedlistview.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import com.gensagames.linkedlistview.anim.ScaleCenterController;
import com.gensagames.linkedlistview.sample.utils.BaseDrawable;
import com.gensagames.linkedlistview.LinkedListView;


/**
 * Created by Genka on 25.01.2016.
 * GensaGames
 */
public class ScaleCenterAdapter extends LinkedListView.Adapter  {

    private Context mainContext;
    private LinkedList<View> mainViewList;

    public ScaleCenterAdapter(Context mainContext) {
        this.mainContext = mainContext;
        mainViewList = new LinkedList<>();
    }

    public void addSimpleView () {
        ViewGroup circleView = BaseDrawable.getSimpleCircle(mainContext);
        circleView.addView(BaseDrawable.getCustomImage(mainContext));
        mainViewList.add(circleView);
        notifyDatasetChanged();
    }

    public void addSimpleView ( int index) {
        ViewGroup circleView = BaseDrawable.getSimpleCircle(mainContext);
        circleView.addView(BaseDrawable.getCustomImage(mainContext));
        mainViewList.add(index, circleView);
        notifyDatasetChanged();
    }

    public void deleteView(int index) {
        mainViewList.remove(index);
        notifyDatasetChanged();
    }

    @Override
        public View getObjectView(int position) {
        if (position < mainViewList.size())
            return mainViewList.get(position);

        return null;
    }

    @Override
    public int getObjectCount() {
        return mainViewList.size();
    }

    public static class AnimationController extends ScaleCenterController {

        public AnimationController() {
        }

        public AnimationController(double maxCenterScale, double minSideScale) {
            super(maxCenterScale, minSideScale);
        }

        public AnimationController(double maxCenterScale, double minSideScale, double deltaScaleView) {
            super(maxCenterScale, minSideScale, deltaScaleView);
        }

        @Override
        public View getFocusView(ViewGroup mainView) {
            return mainView;
        }
    }
}

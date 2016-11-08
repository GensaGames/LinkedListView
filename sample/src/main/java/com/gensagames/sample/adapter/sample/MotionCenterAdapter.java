package com.gensagames.sample.adapter.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.gensagames.linkedlistview.anim.CenterMotionController;
import com.gensagames.sample.util.DefaultSize;
import com.gensagames.sample.adapter.helper.SampleLinkedAdapter;
import com.gensagames.sample.util.BaseDrawable;

import java.util.LinkedList;

/**
 * Created by Genka on 16.07.2016.
 * GensaGames
 */
public class MotionCenterAdapter extends SampleLinkedAdapter {

    private Context mainContext;
    private LinkedList<View> mainViewList;

    public MotionCenterAdapter(Context mainContext) {
        this.mainContext = mainContext;
        mainViewList = new LinkedList<>();
    }

    @Override
    public void addSimpleView () {
        ViewGroup circleView = BaseDrawable.getMotionView(mainContext,
                DefaultSize.CENTER_MOTION_PARENT, DefaultSize.CENTER_MOTION_CIRCLE);
        circleView.addView(BaseDrawable.getCustomImage(mainContext, DefaultSize.CIRCLE_PARENT));
        mainViewList.add(circleView);
        notifyDataSetChanged();
    }

    @Override
    public void deleteView(int index) {
        mainViewList.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public View getObjectView(int position, ViewGroup parentView) {
        if (position < mainViewList.size())
            return mainViewList.get(position);

        return null;
    }

    @Override
    public int getObjectCount() {
        return mainViewList.size();
    }

    @Override
    public void bindView(int position) {

    }

    public static class AnimationController extends CenterMotionController {

        public AnimationController() {
        }
    }
}

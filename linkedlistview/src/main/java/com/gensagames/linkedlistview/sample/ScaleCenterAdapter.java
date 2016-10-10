package com.gensagames.linkedlistview.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.gensagames.linkedlistview.LinkedListView;
import com.gensagames.linkedlistview.sample.utils.BaseDrawable;
import com.gensagames.linkedlistview.sample.utils.DefaultSize;

import java.util.LinkedList;


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
        ViewGroup circleView = BaseDrawable.getSimpleCircle(mainContext, DefaultSize.CIRCLE_PARENT, DefaultSize.CIRCLE_VIEW);
        circleView.addView(BaseDrawable.getCustomImage(mainContext, DefaultSize.CIRCLE_VIEW));
        mainViewList.add(circleView);
        notifyDataSetChanged();
    }

    public void addSimpleView ( int index) {
        ViewGroup circleView = BaseDrawable.getSimpleCircle(mainContext, DefaultSize.CIRCLE_PARENT, DefaultSize.CIRCLE_VIEW);
        circleView.addView(BaseDrawable.getCustomImage(mainContext, DefaultSize.CIRCLE_VIEW));
        mainViewList.add(index, circleView);
        notifyDataSetChanged();
    }

    public void deleteView(int index) {
        if (index >= 0 && !mainViewList.isEmpty()) {
            mainViewList.remove(index);
            notifyDataSetChanged();
        }
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

}

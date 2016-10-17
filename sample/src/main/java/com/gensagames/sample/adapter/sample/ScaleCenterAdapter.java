package com.gensagames.sample.adapter.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.gensagames.linkedlistview.utils.BaseDrawable;
import com.gensagames.linkedlistview.utils.DefaultSize;
import com.gensagames.sample.adapter.helper.SampleLinkedAdapter;

import java.util.LinkedList;


/**
 * Created by Genka on 25.01.2016.
 * GensaGames
 */
public class ScaleCenterAdapter extends SampleLinkedAdapter {

    private Context mainContext;
    private LinkedList<View> mainViewList;

    public ScaleCenterAdapter(Context mainContext) {
        this.mainContext = mainContext;
        mainViewList = new LinkedList<>();
    }

    @Override
    public void addSimpleView () {
        ViewGroup circleView = BaseDrawable.getSimpleCircle(mainContext, DefaultSize.CIRCLE_PARENT, DefaultSize.CIRCLE_VIEW);
        circleView.addView(BaseDrawable.getCustomImage(mainContext, DefaultSize.CIRCLE_VIEW));
        mainViewList.add(circleView);
        notifyDataSetChanged();
    }

    @Override
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
package com.gensagames.sample.adapter.helper;

import com.gensagames.linkedlistview.LinkedListView;

/**
 * Created by GensaGames
 * GensaGames
 */

public abstract class SampleLinkedAdapter extends LinkedListView.Adapter {
    public abstract void addSimpleView();

    public abstract void deleteView(int index);
}

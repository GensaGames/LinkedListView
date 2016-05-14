package com.gensagames.sample.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.gensagames.sample.R;
import com.gensagames.sample.adapter.model.GridItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Genka on 09.05.2016.
 * GensaGames
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MainHolder> {

    private OnItemClickListener onItemClickListener;
    private List<GridItem> gridItemList;
    private Context context;

    public GridAdapter (Context context) {
        gridItemList = new ArrayList<>();
        this.context = context;
    }

    public void addItems (List<GridItem> item) {
        gridItemList.addAll(item);
        notifyDataSetChanged();
    }
    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_grid, null);
        return new MainHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        GridItem item = gridItemList.get(position);
        holder.textView.setText(item.getName());
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, item.getResourceDrawable()));
    }

    @Override
    public int getItemCount() {
        return gridItemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view);
    }

    public class MainHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        KenBurnsView imageView;
        public MainHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.grid_item_text);
            imageView = (KenBurnsView) itemView.findViewById(R.id.grid_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(v);
        }
    }
}

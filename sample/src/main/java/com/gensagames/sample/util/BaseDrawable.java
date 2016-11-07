package com.gensagames.sample.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Genka on 09.02.2016.
 * GensaGames
 */
public class BaseDrawable {

    public static Drawable generateCircleDrawable(int startSize) {
        ShapeDrawable shape = new ShapeDrawable(new OvalShape());
        shape.setIntrinsicHeight(startSize);
        shape.setIntrinsicWidth(startSize);
        shape.getPaint().setColor(Color.WHITE);
        return shape;
    }

    public static ViewGroup getPointView(Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams
                ((int) (DefaultSize.POINT_PARENT * scale + 0.5f) ,
                        (int) (DefaultSize.POINT_PARENT * scale + 0.5f));

        FrameLayout.LayoutParams pointParams = new FrameLayout.LayoutParams
                ((int) (DefaultSize.POINT_VIEW * scale + 0.5f) ,
                        (int) (DefaultSize.POINT_VIEW * scale + 0.5f) ,
                        Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);

        FrameLayout parentLayout = new FrameLayout(context);
        parentLayout.setLayoutParams(parentParams);
        parentLayout.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(pointParams);
        imageView.setImageDrawable(BaseDrawable
                .generateCircleDrawable(DefaultSize.CIRCLE_VIEW));
        imageView.setColorFilter(Color.WHITE);
        imageView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));


        parentLayout.addView(imageView);
        return parentLayout;
    }

    public static ViewGroup getNumericPoint(Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams
                ((int) (DefaultSize.POINT_PARENT * scale + 0.5f) ,
                        (int) (DefaultSize.POINT_PARENT * scale + 0.5f));

        FrameLayout.LayoutParams pointParams = new FrameLayout.LayoutParams
                ((int) (DefaultSize.SIMPLE_POINT_VIEW * scale + 0.5f) ,
                        (int) (DefaultSize.SIMPLE_POINT_VIEW * scale + 0.5f) ,
                        Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);

        FrameLayout parentLayout = new FrameLayout(context);
        parentLayout.setLayoutParams(parentParams);
        parentLayout.setClipChildren(false);
        parentLayout.setClipToPadding(false);
        parentLayout.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));

        TextView textView = new TextView(context);
        textView.setLayoutParams(pointParams);
        int paddingInView = (int) ((DefaultSize.SIMPLE_POINT_VIEW / 4) * scale + 0.5f) ;
        textView.setPadding(paddingInView, paddingInView, 0, 0);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(14);
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(pointParams);
        imageView.setImageDrawable(BaseDrawable
                .generateCircleDrawable(DefaultSize.CIRCLE_VIEW));
        imageView.setColorFilter(Color.WHITE);
        imageView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));


        parentLayout.addView(imageView);
        parentLayout.addView(textView);
        return parentLayout;
    }

    public static ViewGroup getSimpleCircle(Context context, int sizeParent, int sizeInside) {
        final float scale = context.getResources().getDisplayMetrics().density;
        FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams
                ((int) (sizeParent * scale + 0.5f),
                        (int) (sizeParent * scale + 0.5f), Gravity.CENTER);

        FrameLayout.LayoutParams pointParams = new FrameLayout.LayoutParams
                ((int) (sizeInside * scale + 0.5f) ,
                        (int) (sizeInside * scale + 0.5f), Gravity.CENTER);

        FrameLayout parentLayout = new FrameLayout(context);
        parentLayout.setLayoutParams(parentParams);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(pointParams);
        imageView.setImageDrawable(BaseDrawable.generateCircleDrawable(sizeInside));
        parentLayout.addView(imageView);
        return parentLayout;
    }

    public static ImageView getCustomImage(Context context, int viewInside) {
        final float scale = context.getResources().getDisplayMetrics().density;
        FrameLayout.LayoutParams pointParams = new FrameLayout.LayoutParams
                ((int) (viewInside * scale + 0.5f) ,
                        (int) (viewInside * scale + 0.5f), Gravity.CENTER);
        ImageView image = new ImageView(context);
        image.setImageDrawable(ContextCompat.getDrawable(context, android.R.drawable.ic_dialog_map));
        image.setLayoutParams(pointParams);
        image.setColorFilter(ContextCompat.getColor(context, android.R.color.holo_blue_dark));

        int padding =  (int) (10 * scale + 0.5f);
        image.setPadding(padding, padding, padding, padding);
        return image;
    }

    @SuppressWarnings("unused")
    public static View getObjectDivider(Context context) {
        FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams
                (DefaultSize.SIZE_LINE_WIDTH, DefaultSize.CIRCLE_VIEW);
        parentParams.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        FrameLayout.LayoutParams lineParams = new FrameLayout.LayoutParams
                (DefaultSize.SIZE_LINE_WIDTH, DefaultSize.SIZE_LINE_HEIGHT);
        lineParams.gravity = Gravity.START | Gravity.CENTER_VERTICAL;

        FrameLayout parentLayout = new FrameLayout(context);
        parentLayout.setLayoutParams(parentParams);

        View lineView = new View(context);
        lineView.setLayoutParams(lineParams);
        lineView.setBackgroundColor(Color.WHITE);

        parentLayout.addView(lineView);
        return parentLayout;
    }

    public static ViewGroup getMotionView(Context context, int sizeParent, int sizeInside) {
        final float scale = context.getResources().getDisplayMetrics().density;
        FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams
                ((int) (sizeParent * scale + 0.5f),
                        (int) (sizeParent * scale + 0.5f), Gravity.CENTER);

        FrameLayout.LayoutParams insideParams = new FrameLayout.LayoutParams
                ((int) (sizeInside * scale + 0.5f),
                        (int) (sizeInside * scale + 0.5f), Gravity.CENTER);

        FrameLayout parentLayout = new FrameLayout(context);
        parentLayout.setLayoutParams(parentParams);
        parentLayout.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_orange_light));

        FrameLayout insideLayout = new FrameLayout(context);
        insideLayout.setLayoutParams(insideParams);
        insideLayout.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
        parentLayout.addView(insideLayout);
        return parentLayout;
    }

}

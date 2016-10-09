package com.gensagames.linkedlistview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.gensagames.linkedlistview.anim.EmptyController;


/**
 * Created by Genka on 13.12.2015.
 * GensaGames
 */
public class LinkedListView extends HorizontalScrollView
        implements View.OnClickListener, ViewTreeObserver.OnScrollChangedListener {

    private Adapter abstractPagerAdapter;
    private LinearLayout linearMainHolder;
    private AnimationController animationController;
    private ScrollListenState scrollListenState;

    public LinkedListView(Context context) {
        super(context);
        onCreate(context, Gravity.CENTER);
    }

    public LinkedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate(context, Gravity.CENTER);
    }

    /**
     * Method for listening onClick action.
     * This method may be deleted.
     *
     * @param v - onClick View
     */

    @Override
    public void onClick(View v) {
        abstractPagerAdapter.updateItemClick(v);
    }

    /**
     * Notify our AnimationController,
     * whenever ScrollView scroll
     */

    @Override
    public void onScrollChanged() {
        animationController.onScroll(getScrollX());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scrollListenState.onListenTouch(ev);
        return super.onTouchEvent(ev);
    }

    /**
     * Base initializing for main AnimatedViewPager. Contains our main View holder,
     * and his parameters = Gravity. Next, we add this layout to our ScrollView.
     * Setting empty AnimationController and adding ScrollChangeListener.
     *
     * @param context       - main View holder LinerLayout
     * @param holderGravity - gravity for LinerLayout
     */
    private void onCreate(Context context, int holderGravity) {
        scrollListenState = new ScrollListenState();
        linearMainHolder = new LinearLayout(context);
        linearMainHolder.setClipChildren(false);
        linearMainHolder.setClipToPadding(false);
        linearMainHolder.setGravity(holderGravity);
        addView(linearMainHolder);

        animationController = new EmptyController();
        animationController.setContext(this);
        getViewTreeObserver().addOnScrollChangedListener(this);
    }


    /**
     * Setting up AnimationController
     * @param animationController - AnimatedViewPager.AnimationController
     */

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
        this.animationController.setContext(this);
    }

    /**
     * Setting up Adapter and call function, for updating Views
     * @param abstractPagerAdapter - AnimatedViewPager.Adapter
     */
    public void setViewPager(Adapter abstractPagerAdapter) {
        this.abstractPagerAdapter = abstractPagerAdapter;
        this.abstractPagerAdapter.setContext(this);
        updateDataSetChanged();
    }

    /**
     * Updating main View holder data. By adding new views and removing.
     * Methods use deleting, and adding view, as replacing -
     * for preventing duplicates.
     */

    public void updateDataSetChanged() {
        for (int index = 0; index < abstractPagerAdapter.getObjectCount(); index++) {
            View adapterView = abstractPagerAdapter.getObjectView(index);

            if (index > linearMainHolder.getChildCount() - 1) {
                bindView(adapterView);
            } else if (!linearMainHolder.getChildAt(index).equals(adapterView)
                    && adapterView.getParent() != null) {
                unBindView(linearMainHolder.getChildAt(index));
            } else if (!linearMainHolder.getChildAt(index).equals(adapterView)) {
                bindView(adapterView, index);
            }
        }
        for (int increaseCount = linearMainHolder.getChildCount() - 1;
             increaseCount >= abstractPagerAdapter.getObjectCount(); increaseCount--) {
            unBindView(linearMainHolder.getChildAt(increaseCount));
        }
        onScrollChanged();
    }

    /**
     * Methods for detaching view to main Holder layout on ScrollView.
     * Called from method - notifyDataSetChanged();
     *
     * TODO(CustomViewPager): Add onScroll change, to functions below
     */

    private void unBindView(View view) {
        linearMainHolder.removeView(view);
    }

    /**
     * Methods for attaching view to main Holder layout on ScrollView.
     * Called from method - notifyDataSetChanged();
     */

    private void bindView(View v) {
        linearMainHolder.addView(v);
        v.setOnClickListener(this);
    }

    /**
     * Methods for attaching view by index to main Holder layout on ScrollView.
     * Called from method - notifyDataSetChanged();
     */

    private void bindView(View v, int index) {
        linearMainHolder.addView(v, index);
        v.setOnClickListener(this);
    }




    /**
     * --------------------------------------------------------------------
     * *************************** Classes *************************
     * --------------------------------------------------------------------
     */


    private class ScrollListenState implements Runnable {

        private boolean startScrolling;
        private int initialPosition;

        public void onListenTouch(MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                startScrolling = false;
                initialPosition = getScrollX();
                postDelayed(this, ViewConfiguration.getTapTimeout());
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                if (!startScrolling) {
                    startScrolling = true;
                    animationController.onScrollStart();
                }
            }
        }

        @Override
        public void run() {
            int newPosition = getScrollX();
            if (initialPosition - newPosition == 0) {
                animationController.onScrollStop();
            } else {
                initialPosition = getScrollX();
                postDelayed(this, ViewConfiguration.getTapTimeout());
            }
        }
    }

    /**
     * Class returning Animation
     *
     * Interface returning animation for two type action growing
     * up view element and falling down to side.
     * TODO(Doc): Update Doc about adapter methods.
     */

    public static abstract class AnimationController implements OnScrollingAction {
        public static final String ANIM_LOG = "AnimController";
        public static final String ANIM_PARAM_SCROLL_X = "scrollX";

        private ViewGroup mainViewGroup;
        private int scrollViewWidth;
        private int lastScrollOffset;
        private int firstVisiblePosition;
        private int lastVisiblePosition;
        private int scrolledDirection;
        private int centerViewIndex;
        private boolean isCenterStarting;

        private void setContext(LinkedListView linkedListView) {
            this.mainViewGroup = (ViewGroup) linkedListView.getChildAt(0);
        }

        private void onScroll(int scrollViewValue) {
            int scrollViewWidth = getScrollViewWidth();
            int totalScrollScreen = scrollViewValue + scrollViewWidth;
            int totalScrollToCenter = scrollViewValue + (scrollViewWidth / 2);
            int firstVisibleIndex = 0;
            int lastVisibleIndex = 0;
            int viewsOffset = getMainViewHolder().getPaddingStart();

            while (true) {
                View view = getMainViewHolder().getChildAt(lastVisibleIndex);
                if (view == null) {
                    break;
                }
                int viewWidth = view.getWidth();
                viewsOffset += viewWidth;

                if (Math.abs(viewsOffset - totalScrollToCenter) <= viewWidth) {
                    this.centerViewIndex = lastVisibleIndex;
                }

                if (viewsOffset <= scrollViewValue) {
                    firstVisibleIndex++;
                }

                if (viewsOffset < totalScrollScreen) {
                    lastVisibleIndex++;
                } else break;
            }
            if (totalScrollScreen < scrollViewWidth) {
                firstVisibleIndex = 0;
                lastVisibleIndex -= 1;
            }

            this.scrolledDirection = lastScrollOffset - scrollViewValue;
            this.firstVisiblePosition = firstVisibleIndex;
            this.lastVisiblePosition = lastVisibleIndex;
            lastScrollOffset = scrollViewValue;
            onScrollAction();
        }

        public final int getScrollToView(View view) {
            int viewsOffset = 0;
            for (int i = 0; i < getMainViewHolder().indexOfChild(view); i++) {
                viewsOffset += getMainViewHolder().getChildAt(i).getWidth();
            }
            return viewsOffset + getMainViewHolder().getPaddingStart();
        }

        /**
         * Get updated value from getScrollToCenter
         *
         * @param viewOnLayout - focus view
         * @return - new int getScrollToCenter
         * and half of selected view element
         */
        @SuppressWarnings("ConstantConditions")
        public int getTotalScrollToCenter(View viewOnLayout) {
            if (viewOnLayout != null)
                viewOnLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            return getScrollToCenter(viewOnLayout) + (viewOnLayout.getWidth() / 2);
        }

        public int getScrollToCenter(View viewOnLayout) {
            int scrollToCenter = getScroll() + getScrollViewWidth() / 2;
            int scrollToView = getScrollToView(viewOnLayout);
            return scrollToView - scrollToCenter;
        }

        /**
         * Use delegation, and call function AnimationController,
         * for programmatically scroll ScrollView to View position.
         *
         * @param viewFocus      - scroll to View
         * @param scrollDuration - animation duration
         */
        public void animateScrollTo(View viewFocus, long scrollDuration) {
            int scrollToView = getScrollToView(viewFocus) + viewFocus.getWidth() / 2;
            int scrollSize = scrollToView - getScrollViewWidth() / 2;
            ObjectAnimator scrollAnimator = ObjectAnimator.ofInt(getMainViewHolder()
                    .getParent(), ANIM_PARAM_SCROLL_X, scrollSize);
            scrollAnimator.setDuration(scrollDuration);
            scrollAnimator.start();

        }

        public final int getScrollViewWidth() {
            View parent = ((HorizontalScrollView)
                    (getMainViewHolder().getParent()));
            scrollViewWidth = parent.getWidth() - parent.getPaddingStart()
                    - parent.getPaddingEnd();
            return scrollViewWidth;
        }

        public final int getCenterViewIndex() {
            return centerViewIndex;
        }

        public final int getScrolledDirection () {
            return scrolledDirection;
        }

        public final ViewGroup getMainViewHolder() {
            return mainViewGroup;
        }

        public final int getScroll() {
            return lastScrollOffset;
        }

        public final int getFirstVisiblePosition() {
            return firstVisiblePosition;
        }

        public final int getLastVisiblePosition() {
            return lastVisiblePosition;
        }

        public abstract void onScrollAction();
    }


    /**
     * Base class for an Adapter
     *
     * Adapters provide a binding from an app-specific data set to views that are displayed.
     * Implements some base methods. TODO(Doc): Update Doc about adapter methods.
     */

    public static abstract class Adapter {
        private LinkedListView linkedListView;
        private OnItemClickListener onPagerItemClick;

        private void setContext(LinkedListView linkedListView) {
            this.linkedListView = linkedListView;
        }

        public final void notifyDataSetChanged() {
            linkedListView.updateDataSetChanged();
        }

        public final void setOnItemClickListener(OnItemClickListener onPagerItemClick) {
            this.onPagerItemClick = onPagerItemClick;
        }

        public final void updateItemClick(View view) {
            if (onPagerItemClick != null) {
                onPagerItemClick.onItemClick(view);
            }
        }

        public abstract View getObjectView(int position);

        public abstract int getObjectCount();
    }

    /**
     * --------------------------------------------------------------------
     * *************************** Interfaces *****************************
     * --------------------------------------------------------------------
     */


    /**
     * Interface for main view Items to listen action
     * Simple interface for adding click listener and callback.
     */

    public interface OnItemClickListener {
        void onItemClick(View view);
    }


    public interface OnScrollingAction {
        void onScrollStop();
        void onScrollStart();
    }
}

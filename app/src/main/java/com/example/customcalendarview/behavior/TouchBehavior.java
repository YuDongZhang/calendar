package com.example.customcalendarview.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class TouchBehavior extends CoordinatorLayout.Behavior<View> {

    public TouchBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override//方法是一个回调方法，用于指定哪些轴向的滚动将触发此行为。
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int axes, int type) {
         // 只拦截纵向的滚动
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    /**
     *方法用于在嵌套滚动开始之前处理滚动事件。它检查了滚动的方向（dy）以及目标视图（child）的当前位置。
     * 如果child已经滚出了屏幕外或者用户正在向下滚动（dy为负值），就不会进行滚动处理。
     * 否则，它会根据滚动的距离来更新child的translationY属性，从而实现视图的拖动效果。
     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
     *                          associated with
     * @param child the child view of the CoordinatorLayout this Behavior is associated with
     * @param target the descendant view of the CoordinatorLayout performing the nested scroll
     * @param dx the raw horizontal number of pixels that the user attempted to scroll
     * @param dy the raw vertical number of pixels that the user attempted to scroll
     * @param consumed out parameter. consumed[0] should be set to the distance of dx that
     *                 was consumed, consumed[1] should be set to the distance of dy that
     *                 was consumed
     * @param type the type of input which cause this scroll event
     *
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx,
                                  int dy, int[] consumed, int type) {
        // 注意，手指向上滑动的时候，dy大于0。向下的时候dy小于0。
        float translationY = child.getTranslationY(); //translationY y轴平移的意思
        Log.i("TouchBehavior", "onNestedPreScroll: "+translationY);

        // //translationY  通过打印可以看到 , translationY 隐藏之后就是负数
        if (-translationY >= child.getMeasuredHeight() || dy < 0) {
            // // child已经滚动到屏幕外了，或者向下滚动，就不去消耗滚动了
            return;
        }

        //child.getMeasuredHeight(); 测量高度
        // 还差这么desireHeight距离将会移出屏幕外
        float desireHeight = translationY + child.getMeasuredHeight();
        if (dy <= desireHeight) {
            // 将dy全部消耗掉
            child.setTranslationY(translationY - dy);
            consumed[1] = dy;
        } else {
            // 消耗一部分的的dy
            child.setTranslationY(translationY - desireHeight);
            consumed[1] = (int) desireHeight;
        }
    }

    /**
     * 方法用于在嵌套滚动结束时处理滚动事件。它检查了child的当前位置以及未消耗的滚动距离（dyUnconsumed）。
     * 如果child还没有完全滚回到原始位置或者仍有未消耗的滚动距离，就会继续更新child的translationY属性，
     * 从而使child保持在屏幕上或者继续滚动。
     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
     *                          associated with
     * @param child the child view of the CoordinatorLayout this Behavior is associated with
     * @param target the descendant view of the CoordinatorLayout performing the nested scroll
     * @param dxConsumed horizontal pixels consumed by the target's own scrolling operation
     * @param dyConsumed vertical pixels consumed by the target's own scrolling operation
     * @param dxUnconsumed horizontal pixels not consumed by the target's own scrolling
     *                     operation, but requested by the user
     * @param dyUnconsumed vertical pixels not consumed by the target's own scrolling operation,
     *                     but requested by the user
     * @param type the type of input which cause this scroll event
     * @param consumed output. Upon this method returning, should contain the scroll
     *                 distances consumed by this Behavior
     *
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, int[] consumed) {
        float translationY = child.getTranslationY();
        if (translationY >= 0 || dyUnconsumed > 0) {
            // 手指向上滚动或者child已经滚出了屏幕，不去处理
            return;
        }

        if (dyUnconsumed > translationY) {
            //  全部消耗
            consumed[1] += dyUnconsumed;
            child.setTranslationY(translationY - dyUnconsumed);
        } else {
            //消耗一部分
            consumed[1] += (int) translationY;
            child.setTranslationY(0F);
        }
    }
}

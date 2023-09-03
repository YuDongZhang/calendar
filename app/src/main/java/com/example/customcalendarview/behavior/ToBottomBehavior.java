package com.example.customcalendarview.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 参与布局阶段，将RecyclerView置于第一个View的下面
 */
public class ToBottomBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    public ToBottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
        // 低于两个子View的时候不去布局了
        if (parent.getChildCount() < 2) {
            return false;
        }
        View firstView = parent.getChildAt(0);
        //这个是设置左上右下 , 位置的意思 , 左边是0 , 上边是第一个视图的高度 , 右边是宽度 , 下面是 高度
        child.layout(0, firstView.getMeasuredHeight(), child.getMeasuredWidth(), child.getMeasuredHeight());
        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        //依赖这个视图
        return dependency == parent.getChildAt(0);
    }

    // 方法会在依赖视图的位置或状态发生变化时被调用，例如，当依赖视图的位置移动或大小改变时。

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        //这里就是进行重新设置位置了 , 依赖的底部和依赖所移动的距离
        Log.i("TAG", "onDependentViewChanged: "+dependency.getTranslationY());
        child.layout(0, (int)(dependency.getBottom() + dependency.getTranslationY()), child.getMeasuredWidth(), child.getMeasuredHeight());
        return true;
    }
}

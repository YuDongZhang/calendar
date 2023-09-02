package com.example.customcalendarview.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class ScrollBehavior extends CoordinatorLayout.Behavior<TextView> {
    private Context mContext;
    private AttributeSet attributeSet;
    private int mScrollY = 0;
    private int totalScroll = 0;

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        attributeSet = attrs;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, TextView child, int layoutDirection) {
        Log.e("TAG", "onLayoutChild----");
        parent.onLayoutChild(child, layoutDirection);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, TextView child, View directTargetChild, View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, TextView child, View target, int dx, int dy, int[] consumed, int type) {
        int cosumedy = dy;
        Log.e("TAG", "onNestedPreScroll " + totalScroll + " dy " + dy);
        int scroll = totalScroll + dy;
        if (Math.abs(scroll) > getMaxScroll(child)) {
            cosumedy = getMaxScroll(child) - Math.abs(totalScroll);
        } else if (scroll < 0) {
            cosumedy = 0;
        }
        ViewCompat.offsetTopAndBottom(child, -cosumedy);
        totalScroll += cosumedy;
        consumed[1] = cosumedy;
    }

    private int getMaxScroll(TextView child) {
        return child.getHeight();
    }
}

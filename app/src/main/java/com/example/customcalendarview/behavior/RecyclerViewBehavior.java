package com.example.customcalendarview.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

public class RecyclerViewBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    public RecyclerViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        Log.e("TAG", "onDependentViewChanged " + dependency.getBottom() + " " + child.getTop());

        int offset = dependency.getBottom() - child.getTop();
//        if (offset > 0) {
//            ViewCompat.offsetTopAndBottom(child, offset);
//        } else {
//            ViewCompat.offsetTopAndBottom(child, 0);
//        }
        ViewCompat.offsetTopAndBottom(child, (dependency.getBottom() - child.getTop()));
        return true;
    }
}

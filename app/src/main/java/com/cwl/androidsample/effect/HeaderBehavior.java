package com.cwl.androidsample.effect;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class HeaderBehavior extends CoordinatorLayout.Behavior {
    private int mSlop;
    private int sy;

    public HeaderBehavior() {
    }

    public HeaderBehavior(Context context, AttributeSet attrs) {
        mSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        dependency.setTop(child.getBottom());
        return dependency instanceof NestedScrollView;
    }

    //dx,dy相关都是起始点减去结束点


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (child.getTop() > -child.getHeight() && child.getTop() <= 0 && dy >= 0) {
            child.offsetTopAndBottom(-dy);
            consumed[1] = dy;
        }


    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (child.getTop() < 0 && dyUnconsumed <= 0) {
            child.offsetTopAndBottom(-dyUnconsumed);
            //过度恢复
            if (child.getTop() > 0) {
                child.offsetTopAndBottom(-child.getTop());
            }
        }


    }

}

package com.cwl.androidsample.effect;

import android.content.Context;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class ScrollerView extends LinearLayout {
    //平台无关，兼容
    ScrollerCompat mScrollerCompat;
    VelocityTracker mVelocityTracker;
    int velocitySlop;
    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScrollerCompat=ScrollerCompat.create(context);
        velocitySlop= ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    int sy;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();
        int y= (int) event.getY();
        if(mVelocityTracker==null){
            mVelocityTracker=VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                sy=y;
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的是view的内容区域，并不是自身移动
                scrollBy((int) getX(),sy-y);
                sy=y;
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                //可能为负值
                if(Math.abs(mVelocityTracker.getYVelocity())>velocitySlop){
                    if (!mScrollerCompat.isFinished()) {
                        mScrollerCompat.abortAnimation();
                    }
                    //简单的再偏移100
                    mScrollerCompat.startScroll(getScrollX(),getScrollY(),0,100,500);
                }
                if(mVelocityTracker!=null){
                    mVelocityTracker.recycle();
                    //java.lang.IllegalStateException: Already in the pool! 不设为null会报错
                    mVelocityTracker=null;
                }

//                mScrollerCompat.getCurrVelocity();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScrollerCompat.computeScrollOffset()){
            scrollTo(mScrollerCompat.getCurrX(),mScrollerCompat.getCurrY());
            postInvalidate();
        }
    }
}

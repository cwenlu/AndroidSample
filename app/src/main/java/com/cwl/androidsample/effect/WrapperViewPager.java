package com.cwl.androidsample.effect;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class WrapperViewPager extends RelativeLayout {
    private DragViewPager mViewPager;
    private int mSlop;
    private int startY;
    private int originalY;
    private VelocityTracker mVelocityTracker;
    public WrapperViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        int py= (int) ev.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                startY=py;
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(py-startY)>mSlop){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startY=0;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action=event.getAction();
        int py=(int)event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                startY=py;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy=py-startY;
                startY=py;
                //粗略限制位置
                if(mViewPager.getTop()+dy>=0 && mViewPager.getTop()+dy<=originalY){
                    mViewPager.offsetTopAndBottom(dy);
                }

                //缩放所有view
                float sc=originalY-mViewPager.getTop();
                mViewPager.getChildAt(mViewPager.getCurrentItem()).setScaleX((sc/originalY)*0.2f+1);
                mViewPager.getChildAt(mViewPager.getCurrentItem()).setScaleY((sc/originalY)*0.2f+1);

                break;
            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewPager = (DragViewPager) getChildAt(0);
        //计算初始的y值
        mViewPager.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                originalY= (int) mViewPager.getTop();
                mViewPager.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });

    }



}

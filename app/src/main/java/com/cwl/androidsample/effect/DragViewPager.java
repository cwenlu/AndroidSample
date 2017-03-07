package com.cwl.androidsample.effect;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class DragViewPager extends ViewPager{
    private boolean mCanScoll=true;
    public DragViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mCanScoll && super.onTouchEvent(ev);
    }

    public void canScoll(boolean scoll){
        mCanScoll=scoll;
    }

    //重写绘制顺序
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int currentItem = getCurrentItem();
        //总是后一个遮挡前一个，所以只需要交换当前和右边即可，最后一个没有右面的，排除
        if(i==currentItem && i!=childCount-1){
            i=currentItem+1;
        }else if(i==currentItem+1){
            i=currentItem;
        }
        return i;


    }
}

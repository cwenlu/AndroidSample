package com.cwl.androidsample.effect;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.cwl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BActivity extends AppCompatActivity {

    @BindView(R.id.title)
    View title;
    @BindView(R.id.content)
    View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ButterKnife.bind(this);

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                performEnterAnimation();
            }
        });


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        performExitAnimation();
    }

    private void performEnterAnimation(){
        //保持2边位置一致
        int[] outLocations = getIntent().getIntArrayExtra("outLocation");
        int[] location=new int[2];
        title.getLocationOnScreen(location);
        int translateY=location[1]-outLocations[1];
        title.setTranslationY(translateY);

        //位移
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(translateY,translateY-dip2px(this,20f));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                title.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();

        //缩放
        ValueAnimator scaleAnimator=ValueAnimator.ofFloat(1,0.8f);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                title.setScaleX((Float) animation.getAnimatedValue());
            }
        });
        scaleAnimator.setDuration(500);
        scaleAnimator.start();

        //透明
        ValueAnimator alphaAnimator=ValueAnimator.ofFloat(0,1);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                content.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        alphaAnimator.setDuration(500);
        alphaAnimator.start();

    }

    private void performExitAnimation(){
        int translateY= (int) title.getTranslationY();
        //位移
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(translateY,translateY+dip2px(this,20f));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                title.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();

        //缩放
        ValueAnimator scaleAnimator=ValueAnimator.ofFloat(0.8f,1);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                title.setScaleX((Float) animation.getAnimatedValue());
            }
        });
        scaleAnimator.setDuration(500);
        scaleAnimator.start();

        //透明
        ValueAnimator alphaAnimator=ValueAnimator.ofFloat(1,0);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                content.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        alphaAnimator.setDuration(500);
        alphaAnimator.start();

        //结束
        alphaAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
                overridePendingTransition(0,0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);
    }
}

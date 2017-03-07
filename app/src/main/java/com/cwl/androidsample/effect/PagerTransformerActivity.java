package com.cwl.androidsample.effect;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cwl.androidsample.*;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * viewpager个viewpager的父布局都设置android:clipChildren="false"
 */
public class PagerTransformerActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_transformer);
        ButterKnife.bind(this);
        final View[] views=new View[4];
        final int[] color={Color.CYAN,Color.GRAY,Color.GREEN,Color.RED};
        for (int i = 0; i < views.length; i++) {
            View view=new View(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,500));
            view.setBackgroundColor(color[i]);
            views[i]=view;
        }

        vp.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return 4;
            }



            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views[position]);
                return views[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
//        vp.setClipChildren(false);
//        ((ViewGroup) vp.getParent()).setClipChildren(false);
        //必须设置缓存数默认2个可能导致有一边的view不能显示
        vp.setOffscreenPageLimit(3);
        vp.setPageTransformer(true,new ZoomOutPageTransformer());
    }
}

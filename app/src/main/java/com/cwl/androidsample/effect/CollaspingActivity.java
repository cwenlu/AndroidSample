package com.cwl.androidsample.effect;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollaspingActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.abl)
    AppBarLayout abl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collasping);
        ButterKnife.bind(this);
        //粗略演示动画的制作
        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                verticalOffset<=0

                float nx=(float)verticalOffset/abl.getTotalScrollRange()*img.getLeft();
                img.setTranslationX(nx);
                float ny=(float)verticalOffset/abl.getTotalScrollRange()*(img.getTop()-dip2px(CollaspingActivity.this,80));//注意要出去minHeight
                img.setTranslationY(ny);

                //得到比例然后乘以0.3，只拿0.3大小出来缩放
                img.setScaleX(1f+(float)verticalOffset/abl.getTotalScrollRange()*0.3f);
                img.setScaleY(1f+(float)verticalOffset/abl.getTotalScrollRange()*0.3f);

                nx=(float)verticalOffset/abl.getTotalScrollRange()*(title.getLeft()-img.getWidth());
                title.setTranslationX(nx);
                ny=(float)verticalOffset/abl.getTotalScrollRange()*(title.getTop()-dip2px(CollaspingActivity.this,80));
                title.setTranslationY(ny);
            }
        });

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

package com.cwl.androidsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cwl.androidsample.effect.AActivity;
import com.cwl.androidsample.effect.PagerTransformerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.share)
    Button share;
    @BindView(R.id.pager_transformer)
    Button pagerTransformer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.share,R.id.pager_transformer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share:
                startActivity(new Intent(this, AActivity.class));
                break;
            case R.id.pager_transformer:
                startActivity(new Intent(this, PagerTransformerActivity.class));
                break;

        }
    }


}

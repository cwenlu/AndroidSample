package com.cwl.androidsample.effect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cwl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AActivity extends AppCompatActivity {

    @BindView(R.id.title)
    View title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.title)
    public void onClick() {
        int[] outLocation=new int[2];
        title.getLocationOnScreen(outLocation);
        Intent intent=new Intent(this,BActivity.class);
        intent.putExtra("outLocation",outLocation);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
}

package com.waitinghc.cyclerview;

import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CircleView mCircleView;
    private Handler mHandler = new Handler();
    private float mProgress = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCircleView = (CircleView) findViewById(R.id.circleView);
        mCircleView.setStartAngle(0);
        mCircleView.drawStyle(Paint.Style.FILL);
        mCircleView.setProgress(mProgress);

        mHandler.postDelayed(mRunnable, 1000);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mProgress++;
            mCircleView.setProgress(mProgress);
            mHandler.postDelayed(this, 500);
        }
    };
}

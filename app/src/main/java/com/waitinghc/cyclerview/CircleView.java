package com.waitinghc.cyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create by:     何超
 * Create Time:   2017/9/8
 * Brief Desc:    圆形进度
 */

public class CircleView extends View {
    private final static String TAG = CircleView.class.getSimpleName();

    private float mStrokeWidth = 5f;//默认边框宽度
    private float mTextSize = 35f;//默认字体大小
    private Paint mStrokePaint;//绘制边框画笔
    private Paint mTextPaint;//绘制文字画笔
    private Paint mProgressPaint;//进度绘制画笔
    private float mMaxProgress = 100;//最大进度
    private float mProgress = 0;//当前进度
    private String mCenterText = "";//中心文字
    private float mStartAngle = -90f;//默认开始角度
    private Paint.Style mProgressStyle = Paint.Style.STROKE;//是否绘制实心
    private boolean mUseCenter = true;//以中心点开始绘制

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialization(context);
    }

    private void initialization(Context context) {
        mStrokePaint = new Paint();
        mStrokePaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        mProgressPaint.setStrokeWidth(mStrokeWidth);
        mProgressPaint.setStyle(mProgressStyle);
        mProgressPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //计算圆形中心点位置
        float center = getWidth() / 2;

        //圆形半径
        float radius = center - mStrokeWidth * 2;

        //绘制默认圆形
        canvas.drawCircle(center, center, radius, mStrokePaint);

        //绘制当前进度圆形
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        float progress = 360 * mProgress / mMaxProgress;
        canvas.drawArc(oval, mStartAngle, progress, mUseCenter, mProgressPaint);

        //中心文字
        mCenterText = (mProgress / mMaxProgress) * 100 + "%";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(mCenterText, 0, mCenterText.length(), rect);
        canvas.drawText(mCenterText, center - rect.width() / 2, center + rect.height() / 2, mTextPaint);
    }

    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
        invalidate();
    }

    public void setProgress(float progress) {
        mProgress = progress > mMaxProgress ? mMaxProgress : progress;
        if (mProgress <= mMaxProgress)
            invalidate();
    }

    public float getProgress() {
        return mProgress;
    }

    public void setMaxProgress(float maxProgress) {
        mMaxProgress = maxProgress;
        invalidate();
    }

    public float getMaxProgress() {
        return mMaxProgress;
    }

    public void useCenter(boolean useCenter) {
        mUseCenter = useCenter;
    }

    public void drawStyle(Paint.Style style) {
        mProgressStyle = style;
        invalidate();
    }
}

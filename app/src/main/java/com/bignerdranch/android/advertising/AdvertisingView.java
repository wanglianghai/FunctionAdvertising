package com.bignerdranch.android.advertising;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/8/17/017.
 */

public class AdvertisingView extends View {
    private String mString = "广告";

    private int mTopBound = 20;
    private float mBottomBound;
    private float mTextSize;
    private float mWidth = 50;
    private float mCenterX;
    private float mCenterY;
    private float mBoundSize = 20;

    private int mProgress = 355;

    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mBoundPath = new Path();

    public AdvertisingView(Context context) {
        super(context);
    }

    public AdvertisingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AdvertisingView);
        mString = a.getString(R.styleable.AdvertisingView_text_string);
        mTextSize = a.getDimension(R.styleable.AdvertisingView_text_size, 12.0f);
        mWidth = a.getDimension(R.styleable.AdvertisingView_view_size, 20);
        mBoundSize = a.getDimension(R.styleable.AdvertisingView_bound_size, 12);
        a.recycle();
    }

    //这个方法快第一个运行
    {
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mCirclePaint.setColor(Color.YELLOW);
        mBoundPaint.setStyle(Paint.Style.STROKE);
        mBoundPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int rightBound = getWidth() - 20;
        float leftBound = rightBound - mWidth;
        float width = rightBound - leftBound;
        mBottomBound = mTopBound + mWidth;
        mCenterY = mBottomBound - (mBottomBound - mTopBound) / 2;
        mCenterX = rightBound - width / 2;
        //mBoundSize是两边的边界和，一边就是mBoundSize/2
        mBoundPaint.setStrokeWidth(mBoundSize);
        mBoundPath.arcTo(leftBound, mTopBound, rightBound, mBottomBound, -90, mProgress, true);
        canvas.drawCircle(mCenterX, mCenterY, (width - mBoundSize) / 2, mCirclePaint);
        canvas.drawPath(mBoundPath, mBoundPaint);
        mTextPaint.setTextSize(mTextSize);
        canvas.drawText(mString, mCenterX, mCenterY + mTextSize / 2, mTextPaint);
    }

    public void start() {
        new CountDownTimer(5000, 30) {
            private static final String TAG = "CountDownTimer";

            @Override
            public void onTick(long millisUntilFinished) {
                mProgress = (int) (360 * (5000 - millisUntilFinished) / 5000);
//                Log.i(TAG, "onTick: " + millisUntilFinished);
                invalidate();
            }

            @Override
            public void onFinish() {
                //finish时millisUntilFinished  > 0, 有空隙
                mProgress = -90;
                invalidate();
            }
        }.start();
    }
}

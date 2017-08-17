package com.bignerdranch.android.advertising;

import android.content.Context;
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
    private String mString = "跳过";

    private int mTopBound = 20;
    private int mBottomBound = mTopBound + 50;
    private int mTextSize = 20;
    private int mCenterX;
    private int mCenterY;

    private int mProgress;

    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private Path mBoundPath = new Path();

    public AdvertisingView(Context context) {
        super(context);
    }

    public AdvertisingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mCirclePaint.setColor(Color.YELLOW);
        mBoundPaint.setStyle(Paint.Style.STROKE);
        mBoundPaint.setStrokeWidth(5);
        mBoundPaint.setColor(Color.GREEN);

        mCenterY = mBottomBound - (mBottomBound - mTopBound) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int rightBound = getWidth() - 20;
        int leftBound = rightBound - 50;
        mCenterX = rightBound - (rightBound - leftBound) / 2;
        mBoundPath.arcTo(leftBound, mTopBound, rightBound, mBottomBound, -90, mProgress, true);
        canvas.drawCircle(mCenterX, mCenterY, 20, mCirclePaint);
        canvas.drawPath(mBoundPath, mBoundPaint);
        canvas.drawText(mString, mCenterX, mCenterY + mTextSize / 2, mTextPaint);
    }

    public void start() {
        new CountDownTimer(5000, 30) {
            private static final String TAG = "CountDownTimer";

            @Override
            public void onTick(long millisUntilFinished) {
                mProgress = (int) (360 * (5000 - millisUntilFinished) / 5000);
                Log.i(TAG, "onTick: " + millisUntilFinished);
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

package com.example.minihos.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.minihos.R;


public class RoundProgressBar extends View {


    private int mRadius, mColor, mLineWidth, mTextSize, mProgress;
    private Paint mPaint;
    private RectF arcRectF;
    private Rect bound = new Rect();

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setTextSize(mTextSize);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        mRadius = (int) ta.getDimension(R.styleable.RoundProgressBar_radius, dp2px(30));
        mColor = ta.getColor(R.styleable.RoundProgressBar_textColor, 0xffff0000);
        mLineWidth = (int) ta.getDimension(R.styleable.RoundProgressBar_line_width, dp2px(3));
        mTextSize = (int) ta.getDimension(R.styleable.RoundProgressBar_android_textSize, dp2px(16));
        mProgress = ta.getInt(R.styleable.RoundProgressBar_android_progress, 0);
        ta.recycle();
        initPaint();
    }

    private float dp2px(int dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int needWidth = measureWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(needWidth, widthSize);
            } else {
                width = needWidth;
            }
        }
        int height = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int needHeight = measureHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(needHeight, heightSize);
            } else {
                height = needHeight;
            }
        }
        width = Math.min(width,height);
        setMeasuredDimension(width,width);
    }

    private int measureHeight() {
        return mRadius*2;
    }


    private int measureWidth() {
        return mRadius*2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineWidth * 1.0f / 4);
        int width = getWidth();
        int height = getHeight();
        canvas.drawCircle(width / 2, height / 2, width / 2 - getPaddingLeft() - mPaint.getStrokeWidth() / 2, mPaint);
        mPaint.setStrokeWidth(mLineWidth);
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        float angle = mProgress * 1.0f / 100 * 360;
        //外接矩形
        canvas.drawArc(arcRectF, 270, angle, false, mPaint);
        canvas.restore();
        String text = mProgress + "%";
        mPaint.setStrokeWidth(0);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getTextBounds(text,0,text.length(),bound);
        int y = getHeight()/2;
        int textHeight = bound.height();
        canvas.drawText(text,0,text.length(),getWidth()/2, y+textHeight/2,mPaint);
    }


    public static final String INSTANCE = "instance";
    public static final String KEY_PROGRESS = "key_progress";

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PROGRESS, mProgress);
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            Parcelable parcelable = bundle.getParcelable(INSTANCE);
            super.onRestoreInstanceState(parcelable);
            mProgress = bundle.getInt(KEY_PROGRESS);
            return;
        }
    }

    public void setProgress(int progress){
        mProgress = progress;
        invalidate();
    }

    public int getProgress(){
        return mProgress;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        arcRectF = new RectF(0,0,getWidth()-getPaddingLeft()*2,getHeight()-getPaddingTop()*2);
        super.onSizeChanged(w, h, oldw, oldh);
    }


}

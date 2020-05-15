package com.yuan.music_x.widget;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * yuan
 * 2020/4/10
 **/
public class FlowLayout extends ViewGroup {

    private List<List<View>> mLineViews = new ArrayList<List<View>>();
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLineViews.clear();
        mLineHeight.clear();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec) + getPaddingTop() + getPaddingBottom();

        int viewGroupWidth = 0 - getPaddingLeft() - getPaddingRight();
        int viewGroupHeigth = getPaddingTop() + getPaddingBottom();

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            viewGroupHeigth = widthSize;
            viewGroupHeigth = heightSize;
        } else {
            int currentLineWidth = 0;
            int currentLineHeight = 0;

            //存储每一行上的子view
            List<View> lineView = new ArrayList<View>();
            int childViewsCount = getChildCount();
            //对每一个子view进行测量
            for (int i = 0; i < childViewsCount; i++) {
                View childView = getChildAt(i);
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
                int childViewWidth = childView.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                int childViewHeight = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

                if (currentLineWidth + childViewWidth > widthSize) {
                    viewGroupWidth = Math.max(currentLineWidth, widthSize);
                    viewGroupHeigth += currentLineHeight;
                    mLineHeight.add(currentLineHeight);
                    mLineViews.add(lineView);

                    lineView = new ArrayList<>();
                    lineView.add(childView);
                    currentLineWidth = childViewWidth;
                } else {
                    currentLineWidth += childViewWidth;
                    currentLineHeight = Math.max(currentLineHeight, childViewHeight);
                    lineView.add(childView);
                }

                if (i == childViewsCount - 1) {
                    mLineViews.add(lineView);
                    viewGroupWidth = Math.max(childViewWidth, viewGroupWidth);
                    viewGroupHeigth += childViewHeight;
                    mLineHeight.add(currentLineHeight);
                }
                setMeasuredDimension(viewGroupWidth, viewGroupHeigth);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int lines = mLineViews.size();
        for (int i = 0; i < lines; i++) {
            int lineHeight = mLineHeight.get(i);
            List<View> viewList = mLineViews.get(i);
            for (int j = 0; j < viewList.size(); j++) {
                View view = viewList.get(j);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                int vl = left + marginLayoutParams.leftMargin;
                int vt = top + marginLayoutParams.topMargin;
                int vr = vl + view.getMeasuredWidth();
                int vb = vt + view.getMeasuredHeight();
                view.layout(vl, vt, vr, vb);
                left += view.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}

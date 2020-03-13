package com.yuan.music_x.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.yuan.music_x.R;

/**
 * yuan
 * 2020/3/4
 **/
@SuppressLint("AppCompatCustomView")
public class RecommendImageView extends ImageView {

    private String TAG = this.getClass().getSimpleName();

    private Paint paint;
    private Drawable imgDrawable, playDrawable;

    private String num;

    public RecommendImageView(Context context) {
        super(context);
    }

    public RecommendImageView(Context context, String num) {
        super(context);
        this.num = num;
        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.text_dark));
        paint.setTextSize(18);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        imgDrawable = getDrawable();
        imgDrawable.draw(canvas);
        int height = imgDrawable.getIntrinsicHeight();
        playDrawable = getResources().getDrawable(R.drawable.shape_play);
        playDrawable.setBounds(5, height - 30, 30, height - 5);
        playDrawable.draw(canvas);
        canvas.drawText(num, 35, height - 10, paint);
    }
}

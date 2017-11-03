package com.zyl.triangleview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.zyl.triangleview.R;

/**
 * Created by zhaoyongliang on 2017/10/31.
 */

@SuppressLint("AppCompatCustomView")
public class RoundTriangleView extends ImageView {

    private static final String TAG = "TriangleView";

    private int defaultTextSize = 36;

    private int defaultOffset = 20;

    private int width, height;

    // attributes
    private int fillColor, textColor;

    private int textSize;

    private int radius;

    private String text;

    /**
     * 0:leftTop;
     * 1:rightTop;
     * 2:rightBottom;
     * 3:leftBottom
     *
     */
    private int type = 0;

    public void setType(int type) {
        this.type = type;
    }

    public RoundTriangleView(Context context) {
        super(context);
    }

    public RoundTriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public RoundTriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundTriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundTriangleView, 0 , 0);
        fillColor = ta.getColor(R.styleable.RoundTriangleView_rt_fillColor, Color.RED);
        textColor = ta.getColor(R.styleable.RoundTriangleView_rt_textColor, Color.WHITE);

        radius = ta.getInteger(R.styleable.RoundTriangleView_rt_roundRadius, defaultOffset);
        textSize = ta.getDimensionPixelSize(R.styleable.RoundTriangleView_rt_textSize, defaultTextSize);
        text = ta.getString(R.styleable.RoundTriangleView_rt_text);
        type = ta.getInt(R.styleable.RoundTriangleView_rt_type, 0);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "==========onDraw");
        super.onDraw(canvas);
        // 定义画笔
        Paint paint = new Paint();
        // 去锯齿
        paint.setAntiAlias(true);
        // 画笔样式为填充
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 设置画笔颜色
        paint.setColor(fillColor);

        // 定义四个顶点 offset=20
        int offset = radius;

        switch (type) {
            case 0:
                drawLeftTop(canvas, paint, offset);
                break;
            case 1:
                drawRightTop(canvas, paint, offset);
                break;
            case 2:
                drawRightBottom(canvas, paint, offset);
                break;
            case 3:
                drawLeftBottom(canvas, paint, offset);
                break;
        }
    }

    private void drawRightBottom(Canvas canvas, Paint paint, int offset) {
        Point point1 = new Point(width, 0);
        Point point2 = new Point(width, height - offset);
        Point cPoint = new Point(width, height);
        Point point3 = new Point(width - offset, height);
        Point point4 = new Point(0, height);

        Path path = new Path();
        path.moveTo(point2.x, point2.y);
        path.quadTo(cPoint.x, cPoint.y, point3.x, point3.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.lineTo(point1.x, point1.y);
        path.close();

        canvas.drawPath(path, paint);

        // 绘制文字
        paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);

        int x = width * 2 / 3 ;
        int y = height * 2 / 3;

        canvas.rotate(45 + 90, x, y);
        canvas.drawText(text, x, y, paint);
    }

    private void drawLeftBottom(Canvas canvas, Paint paint, int offset) {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(width, height);
        Point cPoint = new Point(0, height);
        Point point3 = new Point(offset, height);
        Point point4 = new Point(0, height - offset);

        Path path = new Path();
        path.moveTo(point3.x, point3.y);
        path.quadTo(cPoint.x, cPoint.y, point4.x, point4.y);
        path.lineTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.close();

        canvas.drawPath(path, paint);

        // 绘制文字
        paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);

        int x = width / 3 ;
        int y = height * 2 / 3;

        canvas.rotate(-135, x, y);
        canvas.drawText(text, x, y, paint);
    }

    private void drawRightTop(Canvas canvas, Paint paint, int offset) {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(width - offset, 0);
        Point cPoint = new Point(width, 0);
        Point point3 = new Point(width, offset);
        Point point4 = new Point(width, height);

        Path path = new Path();
        path.moveTo(point2.x, point2.y);
        path.quadTo(cPoint.x, cPoint.y, point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.lineTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.close();

        canvas.drawPath(path, paint);

        // 绘制文字
        paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);

        int x = width * 2 / 3 ;
        int y = height / 3;

        canvas.rotate(45, x, y);
        canvas.drawText(text, x, y, paint);
    }

    private void drawLeftTop(Canvas canvas, Paint paint, int offset) {
        // 顺时针绘制
        Point cPoint = new Point(0, 0);
        Point point1 = new Point(offset, 0);
        Point point2 = new Point(width, 0);
        Point point3 = new Point(0, height);
        Point point4 = new Point(0, offset);

        Path path = new Path();
        path.moveTo(point4.x, point4.y);
        path.quadTo(cPoint.x, cPoint.y, point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.close();

        canvas.drawPath(path, paint);

        // 绘制文字
        paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);

        int x = width / 3;
        int y = height / 3;

        canvas.rotate(-45, x, y);
        canvas.drawText(text, x, y, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "==========onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        Log.d(TAG, "==========width:" + width + ",height:" + height);
    }
}

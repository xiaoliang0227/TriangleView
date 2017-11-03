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
public class TriangleView extends ImageView {

    private static final String TAG = "TriangleView";

    private int defaultTextSize = 36;

    private int width, height;


    // attributes
    private int fillColor, textColor;

    private int textSize;

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

    public TriangleView(Context context) {
        super(context);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TriangleView, 0 , 0);
        fillColor = ta.getColor(R.styleable.TriangleView_t_fillColor, Color.RED);
        textColor = ta.getColor(R.styleable.TriangleView_t_textColor, Color.WHITE);

        textSize = ta.getDimensionPixelSize(R.styleable.TriangleView_t_textSize, defaultTextSize);
        text = ta.getString(R.styleable.TriangleView_t_text);
        type = ta.getInt(R.styleable.TriangleView_t_type, 0);
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

        // 定义三个顶点
        Point point1 = new Point(0, 0);
        Point point2 = new Point(width, 0);
        Point point3 = new Point(width, height);
        Point point4 = new Point(0, height);


        switch (type) {
            case 0:
                drawLeftTop(canvas, paint, point1, point2, point3, point4);
                break;
            case 1:
                drawRightTop(canvas, paint, point1, point2, point3, point4);
                break;
            case 2:
                drawRightBottom(canvas, paint, point1, point2, point3, point4);
                break;
            case 3:
                drawLeftBottom(canvas, paint, point1, point2, point3, point4);
                break;
            default:
                drawLeftTop(canvas, paint, point1, point2, point3, point4);
                break;
        }
    }

    private void drawLeftBottom(Canvas canvas, Paint paint, Point point1, Point point2, Point point3, Point point4) {
        // 定义绘制路线
        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.lineTo(point1.x, point1.y);

        path.close();

        // 绘制
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

    private void drawRightBottom(Canvas canvas, Paint paint, Point point1, Point point2, Point point3, Point point4) {
        // 定义绘制路线
        Path path = new Path();
        path.moveTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.lineTo(point2.x, point2.y);

        path.close();

        // 绘制
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

    private void drawRightTop(Canvas canvas, Paint paint, Point point1, Point point2, Point point3, Point point4) {
        // 定义绘制路线
        Path path = new Path();

        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point1.x, point1.y);

        path.close();

        // 绘制
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

    private void drawLeftTop(Canvas canvas, Paint paint, Point point1, Point point2, Point point3, Point point4) {
        // 定义绘制路线
        Path path = new Path();

        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point4.x, point4.y);
        path.lineTo(point1.x, point1.y);

        path.close();

        // 绘制
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

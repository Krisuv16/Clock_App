package com.example.clockassignment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.Calendar;

public class Clock extends View {
    private int height, width = 0;
    private int padding = 0;
    private int fontSize = 0;
    private int numeralSpacing = 0;
    private int handTruncation, hourHandTruncation =0;
    private int radius = 0;
    private Paint paint;
    private boolean isInit;
    private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12};
    private Rect rect = new Rect();

    public Clock(Context context) {
        super(context);
    }

    public Clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Clock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initCLock(){
        height = getHeight();
        width = getWidth();
        padding = numeralSpacing + 70;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,30,getResources().getDisplayMetrics());
        int min = Math.min(height,width);
        radius = min/2 - padding;
        handTruncation = min / 20 ;
        hourHandTruncation = min / 7;
        paint = new Paint();
        isInit=true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit){
            initCLock();
        }
        canvas.drawColor(Color.TRANSPARENT);
        drawCircle(canvas);
        drawNumeral(canvas);
        drawHands(canvas);

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawHands(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        Calendar c = Calendar.getInstance();
        float hour = c.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour * 5f) + (c.get(Calendar.MINUTE) * 5f / 60.0) , true);
        drawHand(canvas, c.get(Calendar.MINUTE), false);
        drawHand(canvas, c.get(Calendar.SECOND), false);
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour){
        double angle = Math.PI * loc / 30 - Math.PI / 2 ;
        int handRadius = isHour? radius - handTruncation - hourHandTruncation : radius - handTruncation;
        canvas.drawLine(width / 2 ,height / 2,
                (float) (width / 2 + Math.cos(angle) * handRadius),
                (float) (height / 2 + Math.sin(angle) * handRadius), paint);
    }
    //    THis method is responsible for showing numbers in the clock
    private void drawNumeral(Canvas canvas) {
//        sets the fontSize
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(fontSize);
//        Displaying the numbers
        for (int number : numbers){
            String tmp = String.valueOf(number);
            paint.getTextBounds(tmp, 0 ,tmp.length(), rect);
            double angle = Math.PI/6 * (number - 3);
            int x = (int) (width / 2 + Math.cos(angle) * radius - rect.width() / 2 );
            int y = (int) (height / 2 + Math.sin(angle) * radius + rect.height() / 2 );
            canvas.drawText(tmp, x, y,paint);
        }
    }
    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.black));
//        Sets the boldness of the analog clock.
        paint.setStrokeWidth(11);
    }
 }

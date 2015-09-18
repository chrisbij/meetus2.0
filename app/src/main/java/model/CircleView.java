package model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bijou on 13/08/2015.
 */
public class CircleView extends View {
    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        float cx = getMeasuredWidth() / 2;
        float cy = getMeasuredHeight() / 2;
        float radius = Math.min(cx, cy);
        canvas.drawCircle(cx, cy, radius, paint);
        super.onDraw(canvas);
    }
}

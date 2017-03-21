package test.bwie.com.drag_amplification;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by acer on 2017/3/21.
 */

public class Balls extends View {

    private float height;
    private float width;
    private float radius = 100;
    public Balls(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public Balls(Context context) {
        this(context,null);
    }

    public Balls(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //初始化坐标
         width = this.getWidth()/2;
         height = this.getHeight()/2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawCircle(width,height,radius,paint);

       /* this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getPointerCount()>1){
                    return false;
                }
                invalidate();
            return false;
            }

        });*/
    }
private boolean isOk(float x,float y,float r){
    return x >= r && y >= r && x <= getWidth()-r && y <= getHeight()-r;
}



    @Override
    public boolean onTouchEvent(MotionEvent event) {

            if(event.getPointerCount()>=2){
                float disByXY = getDisByXY(event);

                if (disByXY != 0) {
                    if (isOk(width, height, disByXY)) {
                        radius = disByXY;
                        invalidate();
                    }

                }}else if(event.getPointerCount()==1){

                    float  x = event.getX();
                    float y = event.getY();

                    //判断如果点击到小球上面的话 那么现在点击的位置就是小球挪动到的位置 那么只要重新调用onDraw重新按照位置画小球就可以了
                    if(isOk(x,y,radius)){
                        width = x;
                        height = y;
                        //刷新重绘的方法
                        invalidate();
                    }

                }
        return true;
    }


    private float getDisByXY(MotionEvent event) {

        if (event.getPointerCount() >= 2) {
            float x = event.getX();
            float y = event.getY();

            float x1 = event.getX(1);
            float y1 = event.getY(1);

            return (float) Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
        }
        return 0;
    }

}

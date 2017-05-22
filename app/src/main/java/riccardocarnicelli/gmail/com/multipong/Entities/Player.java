package riccardocarnicelli.gmail.com.multipong.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import riccardocarnicelli.gmail.com.multipong.Utilities.Function;

/**
 * Created by Riccardo on 15/04/2017.
 */
public class Player {

    private float x, y, w, h;
    private int widthScreen;

    public Player(int x, int y, int w, int h, int widthScreen){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.widthScreen = widthScreen;
    }

    public Player(int x, int y, int widthScreen, int heightScreen){
        this.x = x;
        this.y = y;
        this.widthScreen = widthScreen;
        this.w = Function.resolveProportions(480,60,widthScreen);
        this.h = Function.resolveProportions(800,5,heightScreen);
    }

    public void update(float x){
        if (x <= 0)
            return;
        if (x + w > widthScreen)
            return;
        this.x = x;
    }

    public void draw(Canvas c){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        c.drawRect(this.x,this.y, this.x + w, this.y + h, paint);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }
}

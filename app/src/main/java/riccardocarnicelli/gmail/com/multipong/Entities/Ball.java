package riccardocarnicelli.gmail.com.multipong.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

import riccardocarnicelli.gmail.com.multipong.Utilities.Function;
import riccardocarnicelli.gmail.com.multipong.Utilities.PVector;

/**
 * Created bgetY() Riccardo on 15/04/2017.
 */
public class Ball {


    PVector location;
    PVector locationStart;
    int r, topspeed;
    PVector speed;
    PVector acceleration;
    int widthScreen, heightScreen;

    public Ball(int x, int y, int widthScreen, int heightScreen){
        this.location = new PVector(x,y);
        this.locationStart = location.copy();
        r = (int) (Math.sqrt(Function.resolveProportions(480*800, (float)(Math.PI * Math.pow(5,2)), widthScreen*heightScreen)/Math.PI));

        reset();
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
    }

    void reset(){
        this.location = this.locationStart.copy();
        speed = new PVector(2,2);
        acceleration = new PVector((float)0.01,(float)0.01);

        topspeed = 20;

        // Fa partire in versi casuali la pallina
        Random r = new Random();
        int dir = (r.nextInt(2) == 1 ? 1 : -1);
        speed.setY(speed.getY() * dir);
        acceleration.setY(acceleration.getY() * dir);

        dir = (r.nextInt(2) == 1 ? 1 : -1);
        speed.setX(speed.getX() * dir);
        acceleration.setX(acceleration.getX() * dir);

    }

    private boolean collisionRectCircle(float x, float y, float w, float h) {
        float distX = Math.abs(this.location.getX() - x - w / 2);
        float distY = Math.abs(this.location.getY() - y - h / 2);

        if (distX > (w / 2 + r)) {
            return false;
        }
        if (distY > (h / 2 + r)) {
            return false;
        }

        if (distX <= (w / 2)) {
            return true;
        }
        if (distY <= (h / 2)) {
            return true;
        }

        float dx = distX - w / 2;
        float dy = distY - h / 2;
        return (dx * dx + dy * dy <= (r * r));
    }

    public void detectCollisionWithRect(float x, float y, float w, float h, int verso){
        /*
        if ((location.getX() + r + 1 > x && location.getX() + r + 1 <  x + h
                && location.getY() + r + 1 > y && location.getY() + r + 1 <  y + w
                && verso > 0)
                ||
                (location.getX() - r - 1 < x + h && location.getX() - r - 1 >  x
                && location.getY() - r - 1 < y + w && location.getY() - r - 1 >  y)
                && verso < 0){
            speed.setY(speed.getY() * -1);
            acceleration.setY(acceleration.getY() * -1);
        }
        */
        if(collisionRectCircle(x, y, w, h)){
            speed.setY(speed.getY() * -1);
            acceleration.setY(acceleration.getY() * -1);
        }
    }

    public int update(){
        speed.add(acceleration);
        speed.limit(topspeed);
        location.add(speed);
        if (location.getY() + r + 1 > heightScreen) {
            reset();
            return 1;
        }
        if (location.getY() - r -1 < 0){
            reset();
            return -1;
        }
        if ((location.getX() + r > widthScreen) || (location.getX() - r < 0)) {
            speed.setX(speed.getX() * -1);
            acceleration.setX(acceleration.getX() * -1);
        }
        return 0;
    }

    public void draw(Canvas c){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        c.drawCircle(this.location.getX(),this.location.getY(), r, paint);
    }
}

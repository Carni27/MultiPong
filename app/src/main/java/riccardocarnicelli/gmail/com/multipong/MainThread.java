package riccardocarnicelli.gmail.com.multipong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;

import riccardocarnicelli.gmail.com.multipong.Entities.Ball;
import riccardocarnicelli.gmail.com.multipong.Entities.Player;

/**
 * Created by Riccardo on 15/05/2017.
 */
public class MainThread extends Thread{

    private boolean running;

    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;

    private Paint mBitmapPaint;
    private Context context;

    private Ball p;
    private Player g1,g2;
    private float p1X, p2X;
    private int score1, score2;
    private DisplayMetrics metrics;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        this.context = gamePanel.getContext();

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        metrics = context.getResources().getDisplayMetrics();

        p = new Ball(metrics.widthPixels/2,  metrics.heightPixels/2, metrics.widthPixels, metrics.heightPixels);

        g1 = new Player(metrics.widthPixels/2 - 20, 20, metrics.widthPixels, metrics.heightPixels);
        g2 = new Player(metrics.widthPixels/2 - 20, metrics.heightPixels - 20 - 5 , metrics.widthPixels, metrics.heightPixels);

    }

    public void setRunning (boolean running) {
        this.running = running;
    }

    public Paint getBitmapPaint(){
        return mBitmapPaint;
    }

    public void setXP1( float xPos ) {
        this.p1X = xPos;
    }

    public void setXP2( float xPos ) {
        this.p2X = xPos;
    }

    @Override
    public void run() {
        while (running) {
            if(surfaceHolder.getSurface().isValid()){
                Canvas mCanvas = surfaceHolder.lockCanvas();
                if (mCanvas != null) {
                    Paint paint = new Paint();
                    paint.setColor(context.getResources().getColor(R.color.background));
                    mCanvas.drawPaint(paint);

                    paint.setColor(Color.WHITE);
                    mCanvas.drawRect(0, (metrics.heightPixels / 2) - 1, metrics.widthPixels, (metrics.heightPixels / 2) + 1, paint);

                    g1.draw(mCanvas);
                    g1.update(p1X);
                    p1X = 0;

                    g2.update(p2X);
                    p2X = 0;
                    g2.draw(mCanvas);

                    p.detectCollisionWithRect(g1.getX(), g1.getY(), g1.getW(), g1.getH(), -1);
                    p.detectCollisionWithRect(g2.getX(), g2.getY(), g2.getW(), g2.getH(), 1);

                    int score = p.update();

                    if (score < 0)
                        score1++;
                    if (score > 0)
                        score2++;

                    paint.setColor(Color.WHITE);
                    paint.setTextSize(20);
                    mCanvas.drawText("Score DX: " + String.valueOf(score2), (float) 50, (float) metrics.heightPixels / 2 - 50, paint);
                    mCanvas.drawText("Score SX: " + String.valueOf(score1), (float) 50, (float) metrics.heightPixels / 2 + 50, paint);

                    p.draw(mCanvas);

                    surfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }

        }
    }
}

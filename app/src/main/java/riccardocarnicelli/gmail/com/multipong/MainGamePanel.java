package riccardocarnicelli.gmail.com.multipong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Riccardo on 15/05/2017.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap mBitmap;
    private MainThread thread;

    public MainGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        super.getHolder().setFormat(PixelFormat.RGB_565);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Il thread deve arrestarsi e aspettare che finisca
       boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // riproviamo ad arrestare il thread
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = MotionEventCompat.getActionIndex(event);
        int xPos;
        int yPos;

        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();

        if (event.getPointerCount() > 1) {
            for (int i = 0; i < event.getPointerCount(); i++) {
                xPos = (int) event.getX(i);
                yPos = (int) event.getY(i);

                if (metrics.heightPixels / 2 > yPos) {
                    thread.setXP1( xPos );
                } else {
                    thread.setXP2( xPos );
                }
            }
        } else {
            xPos = (int)MotionEventCompat.getX(event, index);
            yPos = (int)MotionEventCompat.getY(event, index);
            if (metrics.heightPixels / 2 > yPos){
                thread.setXP1( xPos );
            }else{
                thread.setXP2( xPos );
            }
        }
        return true;
    }

    public MainThread getMainThread() { return thread; }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, thread.getBitmapPaint());
    }
}

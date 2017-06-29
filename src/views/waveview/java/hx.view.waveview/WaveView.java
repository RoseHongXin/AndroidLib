package hx.view.waveview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rose on 4/12/2017.
 */

public class WaveView extends View{

    private final int MSG_WAVE = 1;
    private final int DRAW_DELAY = 16;
    private final int SHIFT_SPEED = 4;

    private int mWidth;
    private int mHeight;

    // shader containing repeated waves
//    private BitmapShader mWaveShader;
//    private Matrix mShaderMatrix;

    private Paint mWavePaint;
    private List<Point> mWavePoints;
    private Path mWavePath;

    private int mAmplitude;
    private float mAngularFrequency;
    private float mWaveLength;
    private int mShiftSpeed = SHIFT_SPEED;
    private int mShiftedLength;
    private float mInitialXCoordinate;
    private Handler mWaveHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MSG_WAVE){
                mShiftedLength += mShiftSpeed;
                if(mShiftedLength >= mWaveLength) mShiftedLength = 0;
                invalidate();
            }
        }
    };


    public WaveView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        mWavePaint = new Paint();
        mWavePaint.setAntiAlias(true);
        mWavePaint.setColor(Color.parseColor("red"));
        mWavePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWavePaint.setStrokeWidth(4.0f);
        mWavePath = new Path();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            mWidth = getMeasuredWidth();
            mHeight = getMeasuredHeight();
            init();
        }
    }

    private void init(){
        mAmplitude = mHeight / 2;
        mWaveLength = mWidth;
        mInitialXCoordinate = -mWaveLength;
        mWavePoints = new ArrayList<>();
        int pointCnt = 9 * (int)(mWidth / mWaveLength);
        for (int i = 0; i < pointCnt; i++) {
            int y = 0;
            switch (i % 4) {
                case 0:
                    y = mAmplitude;
                    break;
                case 1:
                    y = 0;
                    break;
                case 2:
                    y = mAmplitude;
                    break;
                case 3:
                    y = mAmplitude * 2;
                    break;
            }
            Point point = new Point((int)(mInitialXCoordinate + i * mWaveLength / 4), y);
            mWavePoints.add(point);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWavePath.reset();
        int i = 0;
        mWavePath.moveTo(mWavePoints.get(i).x + mShiftedLength, mWavePoints.get(i).y);
        for (; i < mWavePoints.size() - 2; i += 2) {
            mWavePath.quadTo(mWavePoints.get(i + 1).x + mShiftedLength, mWavePoints.get(i + 1).y, mWavePoints.get(i + 2).x + mShiftedLength, mWavePoints.get(i + 2).y);
        }
        mWavePath.moveTo(mWavePoints.get(i).x, mWavePoints.get(i).y);
        mWavePath.close();
        canvas.drawPath(mWavePath, mWavePaint);
        mWaveHandler.sendEmptyMessageDelayed(MSG_WAVE, DRAW_DELAY);
    }

   /* private void createShader() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);


        // Draw default waves into the bitmap
        // y=Asin(ωx+φ)+h
        float waveX1 = 0;
        final float wave2Shift = mWidth / 4;
        final float endX = getWidth();
        final float endY = getHeight();

        while (waveX1 < endX) {
            double wx = waveX1 * mDefaultAngularFrequency;
            int startY = (int) (mDefaultWaterLevel + mDefaultAmplitude * Math.sin(wx));

            // draw bottom wave with the alpha 40
            canvas.drawLine(waveX1, startY, waveX1, endY, wavePaint1);
            // draw top wave with the alpha 60
            float waveX2 = (waveX1 + wave2Shift) % endX;
            canvas.drawLine(waveX2, startY, waveX2, endY, wavePaint2);

            waveX1++;
        }

        // use the bitamp to create the shader
        mWaveShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        mViewPaint.setShader(mWaveShader);
    }*/

}

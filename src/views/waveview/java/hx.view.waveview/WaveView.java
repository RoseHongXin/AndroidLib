package hx.view.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
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
    private RectF mDrawRect;

    // shader containing repeated waves
//    private BitmapShader mWaveShader;
//    private Matrix mShaderMatrix;

    private List<Point> mPoints;
    private Paint mFirstWavePaint;
    private Paint mSecondWavePaint;

    private Path mFirstWavePath;
    private Path mSecondWavePath;

    private int mAmplitude;
    private float mAngularFrequency;
    private float mWaveLength;
    private int mWaveShiftSpeed = SHIFT_SPEED;
    private int mRippleShiftSpeed = SHIFT_SPEED * 2;
    private int mWaveShiftedLength;
    private int mRippleShiftedLength;
    private float mWaveInitialX;
    private float mWaveInitialY;

    private Handler mDrawHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MSG_WAVE){
                mWaveShiftedLength += mWaveShiftSpeed;
                mRippleShiftedLength += mRippleShiftSpeed;
                if(mWaveShiftedLength >= mWaveLength) mWaveShiftedLength = 0;
                if(mRippleShiftedLength >= mWaveLength) mRippleShiftedLength = 0;
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
        mFirstWavePaint = new Paint();
        mFirstWavePaint.setAntiAlias(true);
        mFirstWavePaint.setColor(Color.parseColor("red"));
        mFirstWavePaint.setStyle(Paint.Style.STROKE);
        mFirstWavePaint.setStrokeWidth(4.0f);

        mSecondWavePaint = new Paint();
        mSecondWavePaint.setAntiAlias(true);
        mSecondWavePaint.setColor(Color.CYAN);
        mSecondWavePaint.setStyle(Paint.Style.FILL);
        mSecondWavePaint.setStrokeWidth(4.0f);
        mSecondWavePaint.setAlpha(100);
        mSecondWavePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));

        mFirstWavePath = new Path();
        mSecondWavePath = new Path();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            mWidth = getMeasuredWidth();
            mHeight = getMeasuredHeight();
            mDrawRect = new RectF(left, top, right, bottom);
            mWaveInitialX = left;
            mWaveInitialY = bottom;
            init();
        }
    }

    private void init(){
        mAmplitude = mHeight / 4;
        mWaveLength = mWidth;
        float initialX = -mWaveLength;
        mPoints = new ArrayList<>();
        int pointCnt = 9 * (int)(mWidth / mWaveLength);
        Point point = new Point((int)initialX, (int)mWaveInitialY);
        mPoints.add(point);
        for (int i = 0; i < pointCnt; i++) {
            int y = 0;
            switch (i % 2) {
                case 0:
                    y = (int)mWaveInitialY;
                    break;
                case 1:
                    y = (int)mWaveInitialY - mAmplitude;
                    break;
            }
            point = new Point((int)(initialX + i * mWaveLength / 4), y);
            mPoints.add(point);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mFirstWavePath.reset();
        mSecondWavePath.reset();
        int i = 0;

//        mFirstWavePath.moveTo(mPoints.get(i).x + mWaveShiftedLength, mPoints.get(i).y);
        mFirstWavePath.moveTo(mPoints.get(i).x + mWaveShiftedLength, mPoints.get(i).y);
        for (; i < mPoints.size() - 2; i += 2) {
            mFirstWavePath.quadTo(mPoints.get(i + 1).x + mWaveShiftedLength, mPoints.get(i + 1).y, mPoints.get(i + 2).x + mWaveShiftedLength, mPoints.get(i + 2).y);
        }
        mFirstWavePath.moveTo(mPoints.get(i).x, mPoints.get(i).y);
        mFirstWavePath.close();

        i = 0;
        mSecondWavePath.moveTo(mPoints.get(i).x + mRippleShiftedLength, mPoints.get(i).y);
        for (; i < mPoints.size() - 2; i += 2) {
            mSecondWavePath.quadTo(mPoints.get(i + 1).x + mRippleShiftedLength, mPoints.get(i + 1).y, mPoints.get(i + 2).x + mRippleShiftedLength, mPoints.get(i + 2).y);
        }
        mSecondWavePath.moveTo(mPoints.get(i).x + mRippleShiftedLength, mPoints.get(i).y);
        mSecondWavePath.close();

        canvas.drawPath(mFirstWavePath, mFirstWavePaint);
        canvas.drawPath(mSecondWavePath, mSecondWavePaint);

//        canvas.saveLayer(mDrawRect, mFirstWavePaint);
//        canvas.saveLayerAlpha(mDrawRect.left, mDrawRect.top, mDrawRect.right, mDrawRect.bottom, 50);

        mDrawHandler.sendEmptyMessageDelayed(MSG_WAVE, DRAW_DELAY);
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

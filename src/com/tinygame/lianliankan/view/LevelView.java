package com.tinygame.lianliankan.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tinygame.lianliankan.R;
import com.tinygame.lianliankan.utils.ImageSplitUtils;

public class LevelView extends View {
    private static final String TAG = "LevelView";
    
    private static final int PADDING = 20;
    
    private Context mContext;
    private HashMap<Integer, Bitmap> mNumberMap;
    private Drawable mLevelLogo;
    private int mCurrentLevel;
    private int mLowNumber;
    private int mHeightNumber;
    private Paint mPaint = new Paint()
;    
    public LevelView(Context context) {
        super(context);
        init(context);
    }

    public LevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    
    public void setLevel(int level) {
        mCurrentLevel = level;
        
        this.invalidate();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        
        int logoWidth = mLevelLogo.getIntrinsicWidth();
        int numberWidth = mNumberMap.get(0).getWidth();
        int numberHeight = mNumberMap.get(0).getHeight();
        int totalWidth = logoWidth + PADDING + numberWidth * 2;
        
        int startX = (width - totalWidth) / 2;
        int logStartY = (height - mLevelLogo.getIntrinsicHeight()) / 2;
        int numberStartY = (height - mNumberMap.get(0).getHeight()) / 2;
        
        mLevelLogo.setBounds(startX, logStartY
                        , startX + mLevelLogo.getIntrinsicWidth()
                        , logStartY + mLevelLogo.getIntrinsicHeight());
        mLevelLogo.draw(canvas);
        
        mLowNumber = mCurrentLevel % 10;
        mHeightNumber = (mCurrentLevel / 10) % 10;
        
//        canvas.drawBitmap(mNumberMap.get(mHeightNumber)
//                            , startX + mLevelLogo.getIntrinsicWidth() + PADDING
//                            , numberStartY
//                            , mPaint);
        Rect src = new Rect(0, 0, numberWidth, numberHeight);
        Rect destLarget = new Rect(startX + mLevelLogo.getIntrinsicWidth() + PADDING
                                , numberStartY
                                , numberWidth + startX + mLevelLogo.getIntrinsicWidth() + PADDING
                                , numberStartY + numberHeight);
        canvas.drawBitmap(mNumberMap.get(mHeightNumber), src, destLarget, mPaint);
        
        Rect destLow = new Rect(startX + mLevelLogo.getIntrinsicWidth() + PADDING + numberWidth
                , numberStartY
                , numberWidth * 2 + startX + mLevelLogo.getIntrinsicWidth() + PADDING
                , numberStartY + numberHeight);
        canvas.drawBitmap(mNumberMap.get(mLowNumber), src, destLow, mPaint);
//        canvas.drawBitmap(mNumberMap.get(mLowNumber)
//                , startX + mLevelLogo.getIntrinsicWidth() + PADDING + numberWidth
//                , numberStartY
//                , mPaint);
        
        LOGD("[[onDraw]] numberWidth = " + numberWidth + " >>>>>>>>>>>>>>");
    }
    
    private void init(Context context) {
        mContext = context;
        mNumberMap = new HashMap<Integer, Bitmap>();
        ArrayList<Bitmap> number = ImageSplitUtils.getInstance().getLevelNumberBtList();
        if (number.size() > 0) {
            for (int i = 0; i < number.size(); ++i) {
                mNumberMap.put(i, number.get(i));
            }
        }
        mLevelLogo = mContext.getResources().getDrawable(R.drawable.level);
    }
    
    private void LOGD(String msg) {
        if (com.tinygame.lianliankan.config.Config.DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
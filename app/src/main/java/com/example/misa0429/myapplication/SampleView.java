package com.example.misa0429.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by misa0429 on 2014/09/11.
 */
class SampleView extends View{
    Paint paint = new Paint();
    int playerY;  //スタート位置
    int playerVX = -50;  //上に10ずつ動く
    Bitmap taxi;
    int width;
    int height;
    int viewWidth;
    int viewHeight;

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        viewWidth = getWidth();
        viewHeight = getHeight();

        playerY = viewHeight;

    }

    public SampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SampleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SampleView(Context context) {
        super(context);
       init();
    }

    private void init () {
        //画像読み込み
        Resources res = this.getContext().getResources();
        taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
        width = taxi.getWidth();
        height = taxi.getHeight();
    }

    int playerX = 100;
    @Override
    public void onDraw(Canvas c){
        //数値処理
        playerY += playerVX;

        if(playerY < viewHeight / 2) {
            playerY = viewHeight;
//            playerX = 300;
        }
        c.drawBitmap(taxi, playerX, playerY, paint);

        if(!isEnd) {
            postInvalidateDelayed(50);
        }
    }

    boolean isEnd = false;
    public void setGameEnd() {
        this.isEnd = true;
    }
}

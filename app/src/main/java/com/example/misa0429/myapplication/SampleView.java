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
    int playerX;  //スタート位置
    int playerVX = -10;  //上に10ずつ動く
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

        playerX = viewHeight;

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

    @Override
    public void onDraw(Canvas c){
        //数値処理
        playerX += playerVX;

        //描画処理
        c.drawBitmap(taxi, viewWidth / 2 - width / 2 , playerX,  paint);

        //ループ処理（onDrawを実行）
        if (playerX> - height) {
           // スピードの調整（ミリ秒）
            postInvalidateDelayed(50);
        }

    }
}

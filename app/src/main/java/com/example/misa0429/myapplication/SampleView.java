package com.example.misa0429.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by misa0429 on 2014/09/11.
 */
class SampleView extends View {
    Paint paint = new Paint();
    int playerY;  //スタートのY座標
    int playerVY = -10;  //上に10ずつ動く
    Bitmap taxi;
    int width; //タクシーの画像の幅
    int height; //タクシーの画像の高さ
    int viewWidth; //画面の幅
    int viewHeight; //画面の高さ

    //5つのレーンのX座標
    int[] lane = new int[]{0, 216, 432, 648, 846};

    int touch_count = 0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        String action = "";

        if (viewWidth / 2 - width / 2 <= x && x <= viewWidth / 2 + width / 2 && playerY <= y && y <= playerY + viewHeight) {
            switch (ev.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    action = "Touch Down";
                    touch_count += 1;
                    //    taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher_reverse);
                    playerVY = 0;

                    //playerVY = -1920;
                    break;
                case MotionEvent.ACTION_MOVE:
                    action = "Touch Move";
                    break;
                case MotionEvent.ACTION_UP:
                    action = "Touch Up";
//                  //  taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    action = "Touch Cancel";
                    break;
            }
           // Log.d("TAG", "action : " + action + " count : " + touch_count);
        }else {
        }
        return true;
    }

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

    private void init() {
        //画像読み込み
        Resources res = this.getContext().getResources();
        taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
        width = taxi.getWidth();
        height = taxi.getHeight();
    }

    int r = new java.util.Random ().nextInt (4);
    int playerX = lane[r];

    @Override
    public void onDraw(Canvas c) {

        //数値処理
        playerY += playerVY;

        //上まで行ったら下に戻る動き
       if(playerY < -height) {
           playerY = viewHeight;
           r = new java.util.Random ().nextInt (4);
           playerX = lane[r];

        }

        //描画処理
        c.drawBitmap (taxi, playerX, playerY, paint);

        // ループ処理、スピードの調整（ミリ秒）
            postInvalidateDelayed(50);

    }
}

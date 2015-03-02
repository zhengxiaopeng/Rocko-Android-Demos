package org.rocko.demos.colorfilter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private ImageView head;
    private FrameLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "点击头像背景", Toast.LENGTH_SHORT).show();

        bg = (FrameLayout) findViewById(R.id.head_bg);
        head = (ImageView) findViewById(R.id.head);

        head.setImageDrawable(new CircleDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.small)));

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAvatarBackground();
            }

        });
    }

    private void initAvatarBackground() {
        Drawable drawable = getResources().getDrawable(R.drawable.rocko);
        Bitmap srcBitmap = BitmapUtils.drawable2Bitmap(drawable);

        /*先黑白图片*/
        float[] src = new float[]{
                0.28F, 0.60F, 0.40F, 0, 0,
                0.28F, 0.60F, 0.40F, 0, 0,
                0.28F, 0.60F, 0.40F, 0, 0,
                0, 0, 0, 1, 0,
        };
        ColorMatrix cm = new ColorMatrix(src);
//        cm.setSaturation(0.0f);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        Bitmap resultBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(100);
        paint.setColorFilter(f);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        /*后模糊图片*/
        Bitmap bB = BitmapUtils.blurBitmap(getApplicationContext(), resultBitmap, 15.5f);

        bg.setBackgroundDrawable(new BitmapDrawable(getResources(), bB));
    }

}

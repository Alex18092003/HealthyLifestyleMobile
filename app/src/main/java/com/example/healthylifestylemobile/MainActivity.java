package com.example.healthylifestylemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    private final int SPLASH_DISPLAY_LENGHT = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Используйте интерполятор отказов с амплитудой 0,2 и частотой 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        imageView.startAnimation(myAnim);
        //задержка для анимаци кнопки  // переход
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(MainActivity.this, HomeAutorization.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this, HomeAutorization.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();;
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
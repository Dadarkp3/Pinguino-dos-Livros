package com.facul.meuslivros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    Animation topAnimation, bottomAnimation;
    ImageView logo_image;
    TextView logo_text, logo_subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo_image = findViewById(R.id.logo_img);
        logo_text = findViewById(R.id.logo_text);
        logo_subtitle = findViewById(R.id.subtitle_logo);

        logo_image.setAnimation(topAnimation);
        logo_text.setAnimation(bottomAnimation);
        logo_subtitle.setAnimation(bottomAnimation);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override public void run() {
                showDashboard();
            }
        }, 3000);

    }

    private void showDashboard() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
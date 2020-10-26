package com.test.aliveCore.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.test.aliveCore.R;


public class SplashActivity extends Activity implements View.OnClickListener {

    Handler handler;
    Runnable runnable;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Attaches a listener for animation/transition to the Mr. Jitters logo
        logo = (ImageView) findViewById(R.id.mrjitters);
        logo.setOnClickListener(this);

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                logo.performClick();
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Adds a jitter animation to the Mr. Jitters logo
        RotateAnimation jitter = new RotateAnimation(0, 2, 50, 50);
        jitter.setDuration(10);
        jitter.setRepeatCount(Animation.INFINITE);
        jitter.setRepeatMode(Animation.REVERSE);
        logo.startAnimation(jitter);
    }

    @Override
    public void onClick(View view) {

        handler.removeCallbacks(runnable);
        // Defines a new alpha/scale animation
        Animation click = AnimationUtils.loadAnimation(this, R.anim.click);

        // Defines a listener to transition to the PlacePickerActivity after the animation completes
        click.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        // Attaches the alpha/scale animation to the view
        view.startAnimation(click);
    }
}

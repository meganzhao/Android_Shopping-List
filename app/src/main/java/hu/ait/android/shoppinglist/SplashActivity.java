package hu.ait.android.shoppinglist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhaozhaoxia on 11/4/17.
 */

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StartAnimation();

        Timer timer = new Timer();
       final Intent intent = new Intent(this, MainActivity.class);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
              startActivity(intent);
                finish();
            }
        }, 3000);

    }

    private void StartAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView logo = (ImageView)findViewById(R.id.logo);
        logo.clearAnimation();
        logo.startAnimation(anim);
    }
}


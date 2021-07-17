package com.example.mydairy.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydairy.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                firebaseAuth = FirebaseAuth.getInstance();
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user == null) {
//                    Intent intent = new Intent(SplashActivity.this, PhoneLoginActivity.class);
//                    SplashActivity.this.startActivity(intent);
//                    SplashActivity.this.finish();
//                }
//                else
//                {
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    SplashActivity.this.startActivity(intent);
//                    SplashActivity.this.finish();
//                }

            }
        }, 2200);
    }
}

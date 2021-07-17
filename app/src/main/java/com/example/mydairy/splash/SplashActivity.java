package com.example.mydairy.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydairy.phonelogin.PhoneLoginActivity;
import com.example.mydairy.R;

public class SplashActivity extends AppCompatActivity {

//    private FirebaseAuth firebaseAuth;
//    public static int flag_for_loc_dialog =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, PhoneLoginActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
//                firebaseAuth = FirebaseAuth.getInstance();
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user == null) {
//                    Intent intent = new Intent(SplashActivity.this, PhoneLoginActivity.class);
//                    SplashActivity.this.startActivity(intent);
//                    SplashActivity.this.finish();
//                }
//                else
//                {
//
//                }

            }
        }, 2200);
    }
}

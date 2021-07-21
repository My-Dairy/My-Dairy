package com.example.mydairy.splash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydairy.MainActivity;
import com.example.mydairy.phonelogin.PhoneLoginActivity;
import com.example.mydairy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PlayGamesAuthCredential;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    public static int flag_for_loc_dialog =0;
    String selectedLanguage = "English";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // loadLocale();
        setLocale("gu");
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user == null){
                    showAlertDialog();

                }
                else
                {

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);

                    SplashActivity.this.finish();
                }

            }
        }, 2200);
    }

        private void showAlertDialog() {
        String[] languages = {"English","Gujarati"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle("Choose a Language");
        builder.setSingleChoiceItems(languages, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                selectedLanguage = languages[which];


            }
        });
        builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                System.out.println("jk1 "+selectedLanguage);
                if(selectedLanguage.equals("English"))
                {Log.i("Snt","Ahhiya3");
                    setLocale("en");
                }
                else if(selectedLanguage.equals("Gujarati"))


                { Log.i("Snt","Ahhiya4");
                    setLocale("gu");
                }
                dialog.dismiss();
                Log.i("Snt","Ahhiya");
                Intent intent = new Intent(SplashActivity.this, PhoneLoginActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        });

        builder.show();

    }


    private void setLocale(String localeCode){
        System.out.println("jk" + localeCode);
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.setLocale(new Locale(localeCode.toLowerCase()));
        resources.updateConfiguration(config, dm);
    }

}

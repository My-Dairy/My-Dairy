package com.example.mydairy.splash;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydairy.Details.UserDetails;
import com.example.mydairy.MainActivity;
import com.example.mydairy.phonelogin.PhoneLoginActivity;
import com.example.mydairy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    //Initializing the variables.
    private Dialog dialog;
    private FirebaseAuth firebaseAuth;
    public static int flag_for_loc_dialog =0;
    String selectedLanguage = "English";
    int flag = 0;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    String[] language = {"English", "Gujarati"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Custom Function made below for changing language;
        setLocale("gu");

        setContentView(R.layout.activity_splash);

        //Using app in fullscreen mode.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View logo = findViewById(R.id.cow_logo);
        View logo1 = findViewById(R.id.dairy_part);
        View logo2 = findViewById(R.id.title_desc);

        //Initializing the user and getting the user.
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // If user is not added until now.
        if(user == null){
            flag = 0;
        }
        else
        {
            // Getting user id and checking if the user's folder in database is made or not.
            String keyid = user.getUid();
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference();

            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // If the user's database is made.
                    if(snapshot.hasChild(keyid))
                    {
                        flag = 1;
                    }
                    else
                    {
                        flag = 2;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        //Using timer handler method to sleep app for 2 seconds.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                logo.animate().translationX(-1500).setDuration(500);
                logo1.animate().translationX(-1500).setDuration(500);
                logo2.animate().translationX(-1500).setDuration(500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(flag == 0){
                            showAlertDialog();
                        }
                        else if(flag==1)
                        {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            SplashActivity.this.startActivity(intent);
                            SplashActivity.this.finish();
                        }
                        else
                        {
                            // If the user's database is not made.
                            Intent intent = new Intent(SplashActivity.this, UserDetails.class);
                            SplashActivity.this.startActivity(intent);
                            SplashActivity.this.finish();
                        }

                    }
                }, 300);

            }
        }, 2200);

    }

    private void showAlertDialog() {

        // Initializing for dialog box.
        dialog = new Dialog(SplashActivity.this);
        dialog.setContentView(R.layout.dialog_lnguage_wlcm);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_welcome);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        // Initializing Spinner.
        Spinner spinner = dialog.findViewById(R.id.language_spinner);
        ArrayAdapter ad = new ArrayAdapter(this, R.layout.spinner_text, language);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting adapter of array Language.
        spinner.setAdapter(ad);

        //Showing dialog box.
        Button Next = (Button) dialog.findViewById(R.id.next_btn);
        dialog.show();

        // Checking if next button is clicked.
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = spinner.getSelectedItemPosition();

                if(position==0)
                {
                    setLocale("en");
                }
                else
                {
                    setLocale("gu");
                }

                dialog.dismiss();
                Intent intent = new Intent(SplashActivity.this, PhoneLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setLocale(String localeCode){
        // Code for updating the language.
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.setLocale(new Locale(localeCode.toLowerCase()));
        resources.updateConfiguration(config, dm);
    }

}

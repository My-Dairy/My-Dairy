package com.example.mydairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class PhoneLoginActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText phone_number;
    Button sendotp;
    TextView Email;
    String PhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);


        phone_number=(EditText)findViewById(R.id.phone_number);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phone_number);
        sendotp=(Button)findViewById(R.id.generate_otp_btn);
        Email = (TextView) findViewById(R.id.btn_sign_in_with_email);

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ccp.isValidFullNumber())
                {
                    PhoneNumber = phone_number.getText().toString();
                    Intent int_confirm = new Intent(PhoneLoginActivity.this, PhoneOTPActivity.class);
                    int_confirm.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                    startActivity(int_confirm);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
//
//        Email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent int_signup = new Intent(PhoneLoginActivity.this, EmailLoginActivity.class);
//                startActivity(int_signup);
//            }
//        });

    }
}
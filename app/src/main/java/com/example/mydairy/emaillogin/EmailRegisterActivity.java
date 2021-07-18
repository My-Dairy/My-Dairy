package com.example.mydairy.emaillogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class EmailRegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText Email;
    TextInputLayout Password;
    TextInputLayout Confirm;
    Button Register;
    EditText pwd, cnfrm;
    Button Login;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$");
    String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_register);

        mAuth = FirebaseAuth.getInstance();

        Email = (EditText) findViewById(R.id.email_box);
        Password = (TextInputLayout) findViewById(R.id.password_box);
        Confirm = (TextInputLayout) findViewById(R.id.confirm_password_box);
        Register = (Button) findViewById(R.id.register_btn);

        pwd = (EditText) findViewById(R.id.pwd);
        cnfrm = (EditText) findViewById(R.id.cnfrm);

        Login = (Button) findViewById(R.id.btn_log_in);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString().isEmpty()) {
                    Email.setError("Please Enter an email");
                }
                else {
                    if (Email.getText().toString().trim().matches(EmailPattern)) {
                        if(pwd.getText().toString().isEmpty())
                        {
                            pwd.setError("Please enter password");
                        }
                        else if(!PASSWORD_PATTERN.matcher(pwd.getText().toString()).matches())
                        {
                            pwd.setError("Password must contain 1 lowercase or uppercase, 1 digit and 1 symbol.");
                        }
                        else
                        {
                            if(cnfrm.getText().toString().equals(pwd.getText().toString()))
                            {
                                mAuth.createUserWithEmailAndPassword(Email.getText().toString(), pwd.getText().toString())
                                        .addOnCompleteListener(EmailRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    // Sign in success, update UI with the signed-in user's information
                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(EmailRegisterActivity.this, "Verify Your email and LogIn", Toast.LENGTH_SHORT).show();

                                                                if (user.isEmailVerified()) {
                                                                    Intent int_main = new Intent(EmailRegisterActivity.this, MainActivity.class);
                                                                    finishAffinity();
                                                                    startActivity(int_main);
                                                                    finish();
                                                                } else {
                                                                    Intent int_main = new Intent(EmailRegisterActivity.this, EmailLoginActivity.class);
                                                                    startActivity(int_main);
                                                                    finish();
                                                                }
                                                            } else {
                                                                Toast.makeText(EmailRegisterActivity.this, "Verification Code Error.",
                                                                        Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });

                                                } else {
                                                    // If sign in fails, display a message to the user.
                                                    Toast.makeText(EmailRegisterActivity.this, "Authentication failed.",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            }
                            else
                            {
                                cnfrm.setError("Password does not match");
                            }
                        }
                    }
                    else
                    {
                        Email.setError("Please Enter Valid email.");
                    }
                }
            }
        });
    }
}
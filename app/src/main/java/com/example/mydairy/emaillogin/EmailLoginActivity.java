package com.example.mydairy.emaillogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydairy.Details.UserDetails;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class EmailLoginActivity extends AppCompatActivity {

    SignInButton signInButton;
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    Button LetMeIn;
    TextView SignUp;
    EditText Email;
    TextView Forgot;
    TextInputLayout Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        mAuth = FirebaseAuth.getInstance();
        LetMeIn = (Button) findViewById(R.id.let_me_in);
        Email = (EditText) findViewById(R.id.username_box);
        Password = (TextInputLayout) findViewById(R.id.password_box);
        Forgot = (TextView) findViewById(R.id.forgt_pswd);

        SignUp = (Button) findViewById(R.id.btn_create_account);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmailLoginActivity.this, EmailRegisterActivity.class));
            }
        });


        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(Email.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),"Kindly check your email", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Please enter valid email", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        LetMeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(Email.getText().toString(), Password.getEditText().getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if (user.isEmailVerified()) {

                                            Intent intent = new Intent(EmailLoginActivity.this, UserDetails.class);
                                            intent.putExtra("UID",user.getUid());
                                            System.out.println("JINIL3"+user.getUid());
                                            finishAffinity();
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Kindly Verify Your email",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = GoogleSignIn.getClient(this, gso);


        signInButton=(SignInButton)findViewById(R.id.ggl_logo);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = googleApiClient.getSignInIntent();
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                System.out.println("Error");
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            finish();
                            Intent int_main = new Intent(EmailLoginActivity.this, MainActivity.class);
                            startActivity(int_main);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.println("Error");
                        }
                    }
                });
    }


}
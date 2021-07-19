package com.example.mydairy.Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetails extends AppCompatActivity {


    private EditText editText ,editText2,editText3;
    private Button button;
    private  User user;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        editText  = findViewById(R.id.editTextTextPersonName);
        editText2  = findViewById(R.id.editTextTextPersonName2);
        editText3  = findViewById(R.id.editTextTextPersonName3);
        button= findViewById(R.id.button);


        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText.getText().toString();
                String surname = editText2.getText().toString();
                String location = editText3 .getText().toString();
                user = new User(name,surname,location);
                updateUI();
            }
        });

    }


        public void updateUI() {
            String keyid;
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                keyid= null;
            } else {
                keyid= extras.getString("UID");
            }
            //String keyid = databaseReference.push().getKey();
            databaseReference.child("users").child(keyid).setValue(user); //adding user info to database

            Intent int_main = new Intent(UserDetails.this, MainActivity.class);
            finishAffinity();
            startActivity(int_main);

    }


}
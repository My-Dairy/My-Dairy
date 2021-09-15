package com.example.mydairy.specific;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.mydairy.Details.DailyEntry;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.ReportAdapter;
import com.example.mydairy.adapter.ReportMilkBillAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReportMilkBill extends AppCompatActivity {

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<DailyEntry> dailyEntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_milk_bill);

        listView = (ListView) findViewById(R.id.milk_bill_recycler);
        dailyEntryList = new ArrayList<>();
        loaddata();

    }

    public void loaddata()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DailyEntry");

        mPostReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String date = snapshot.getKey();

                    for(DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        String time = snapshot1.getKey();

                        String Fat = snapshot1.child("fat").getValue().toString();
                        String Qty = snapshot1.child("quantity").getValue().toString();
                        String Amt = snapshot1.child("amount").getValue().toString();

                        DailyEntry dailyEntry = new DailyEntry(Fat,Qty,Amt,date,time);

                        dailyEntryList.add(dailyEntry);
                    }
                }

                ReportMilkBillAdapter adapter = new ReportMilkBillAdapter(ReportMilkBill.this, dailyEntryList);

                listView.setAdapter(adapter);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("SPPatel");
            }
        });
    }
}
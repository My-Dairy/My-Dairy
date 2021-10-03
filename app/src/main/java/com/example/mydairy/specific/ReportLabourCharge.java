package com.example.mydairy.specific;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.mydairy.Details.DailyEntry;
import com.example.mydairy.Details.DanKhanEntry;
import com.example.mydairy.Details.DavaDaru;
import com.example.mydairy.Details.GassCharo;
import com.example.mydairy.Details.LabourCharge;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.GaasCharoAdapter;
import com.example.mydairy.adapter.ReportAdapter;
import com.example.mydairy.adapter.ReportDanKhanAdapter;
import com.example.mydairy.adapter.ReportDavaDaruAdapter;
import com.example.mydairy.adapter.ReportLabourChargeAdapter;
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
import androidx.appcompat.app.AppCompatActivity;

public class ReportLabourCharge extends AppCompatActivity{

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<LabourCharge> labourChargeArrayList;
    float f1,f2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_labour_charge);

        listView = (ListView) findViewById(R.id.labour_charge_recycler);
        labourChargeArrayList = new ArrayList<>();
        loaddata();

    }

    private void loaddata() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("LabourCharge");

        mPostReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String date1 = snapshot.getKey();
                    String date="";
                    for(int i=0;i<date1.length();i++)
                    {
                        if(date1.charAt(i)==' ')
                        {
                            break;
                        }
                        else
                            date+=date1.charAt(i);
                    }
                    Log.i("jinil1234",date);


                    String salary= snapshot.child("monthly").getValue().toString();
                    if(salary==null)
                    {
                        salary="0";
                    }
                    //else continue;
                    System.out.println("Jinil salary"+salary);

                    String withdraw= snapshot.child("withdraw").getValue().toString();
                    if(withdraw==null)
                    {
                        withdraw="0";
                    }
                    //else continue;
                    System.out.println("Jinil salary"+withdraw);

                    String balance= snapshot.child("balance").getValue().toString();
                    if(balance==null)
                    {
                        balance="0";
                    }
                    //else continue;
                    System.out.println("Jinil salary"+balance);

                    String type= snapshot.child("type").getValue().toString();
                    if(type==null)
                    {
                        type="0";
                    }
                    //else continue;
                    System.out.println("Jinil salary"+type);

                    String amount= snapshot.child("amount").getValue().toString();
                    if(amount==null)
                    {
                        amount="0";
                    }
                    //else continue;
                    System.out.println("Jinil salary"+amount);





                    LabourCharge labourCharge = new LabourCharge(salary,withdraw,balance,type,amount,date);
//
                    labourChargeArrayList.add(labourCharge);
//                    }
                }

                ReportLabourChargeAdapter adapter = new ReportLabourChargeAdapter(ReportLabourCharge.this, labourChargeArrayList);

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

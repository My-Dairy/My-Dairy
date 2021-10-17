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
import com.example.mydairy.Details.Misc;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.GaasCharoAdapter;
import com.example.mydairy.adapter.ReportAdapter;
import com.example.mydairy.adapter.ReportDanKhanAdapter;
import com.example.mydairy.adapter.ReportDavaDaruAdapter;
import com.example.mydairy.adapter.ReportMilkBillAdapter;
import com.example.mydairy.adapter.ReportMiscAdapter;
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
public class ReportMisc extends AppCompatActivity{

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<Misc> miscList;
    float f1,f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_misc);

        listView = (ListView) findViewById(R.id.misc_recycler);
        miscList = new ArrayList<>();
        loaddata();

    }

    private void loaddata() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("Misc");

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


                    String electricity_bill= snapshot.child("electricityBill").getValue().toString();
                    if(electricity_bill=="")
                    {
                        electricity_bill="0";
                    }
                    //else continue;
                    System.out.println("Jinil electricity_bill"+electricity_bill);

                    String gas_bill= snapshot.child("gasBill").getValue().toString();
                    if(gas_bill=="")
                    {
                        gas_bill="0";
                    }
                    //else continue;
                    System.out.println("Jinil gas_bill"+gas_bill);

                    String maintenance_amount= snapshot.child("maintenanceAmount").getValue().toString();
                    if(maintenance_amount=="")
                    {
                        maintenance_amount="0";
                    }
                    //else continue;
                    System.out.println("Jinil maintenance_amount"+maintenance_amount);

                    String maintenance_details= snapshot.child("maintenanceDetails").getValue().toString();
                    if(maintenance_details=="")
                    {
                        maintenance_details="0";
                    }
                    //else continue;
                    System.out.println("Jinil maintenance_details"+maintenance_details);

                    String other_amount= snapshot.child("otherAmount").getValue().toString();
                    if(other_amount=="")
                    {
                        other_amount="0";
                    }
                    //else continue;
                    System.out.println("Jinil other_amount"+other_amount);

                    String other_details= snapshot.child("otherDetails").getValue().toString();
                    if(other_details=="")
                    {
                        other_details="0";
                    }
                    //else continue;
                    System.out.println("Jinil other_details"+other_details);



                    Misc misc = new Misc(gas_bill,electricity_bill,maintenance_amount,maintenance_details,other_details,other_amount,date);
//
                    miscList.add(misc);
//                    }
                }

                ReportMiscAdapter adapter = new ReportMiscAdapter(ReportMisc.this,miscList);

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

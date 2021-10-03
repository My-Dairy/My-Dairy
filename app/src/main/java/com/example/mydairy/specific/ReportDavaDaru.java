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
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.GaasCharoAdapter;
import com.example.mydairy.adapter.ReportAdapter;
import com.example.mydairy.adapter.ReportDanKhanAdapter;
import com.example.mydairy.adapter.ReportDavaDaruAdapter;
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


public class ReportDavaDaru extends AppCompatActivity{

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<DavaDaru> davaDaruList;
    float f1,f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_dava_daru);

        listView = (ListView) findViewById(R.id.dava_daru_recycler);
        davaDaruList = new ArrayList<>();
        loaddata();

    }

    private void loaddata() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DavaDaru");

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


                    String medicine_external= snapshot.child("medicine_external").getValue().toString();
                    if(medicine_external=="")
                    {
                        medicine_external="0";
                    }
                    //else continue;
                    System.out.println("Jinil medicine"+medicine_external);


                    String medical_visit_external = snapshot.child("medical_visit_external").getValue().toString();
                    String medical_visit_dairy = snapshot.child("medical_visit_dairy").getValue().toString();

                    if((medical_visit_external==null || medical_visit_external.isEmpty()) && (medical_visit_dairy==null || medical_visit_dairy.isEmpty()))
                    {
                        medical_visit_external="0.0";
                        medical_visit_dairy="0.0";
                    }
                    else if(medical_visit_external.isEmpty() || medical_visit_external==null)
                    {
                        medical_visit_external="0.0";
                    }
                    else if(medical_visit_dairy==null || medical_visit_dairy.isEmpty())
                    {
                        medical_visit_dairy="0.0";
                    }

                    f1=Float.parseFloat(medical_visit_external);
                    f2=Float.parseFloat(medical_visit_dairy);
                    medical_visit_external= String.valueOf(f1+f2);
                    System.out.println("Jinil medical external "+f1+" "+f2+" "+medical_visit_external);

                    String dose_dairy = snapshot.child("dose_dairy").getValue().toString();
                    String dose_external = snapshot.child("dose_external").getValue().toString();

                    if((dose_dairy==null || dose_dairy.isEmpty()) && (dose_external==null || dose_external.isEmpty()))
                    {
                        dose_external="0.0";
                        dose_dairy="0.0";
                    }
                    else if(dose_dairy==null || dose_dairy.isEmpty())
                    {
                        dose_dairy="0.0";
                    }
                    else if(dose_external==null || dose_external.isEmpty())
                    {
                        dose_external="0.0";
                    }

                    f1=Float.parseFloat(dose_dairy);
                    f2=Float.parseFloat(dose_external);
                    dose_external= String.valueOf(f1+f2);
                    System.out.println("Jinil dose "+f1+" "+f2+" "+dose_external);

                    DavaDaru davaDaru = new DavaDaru(medicine_external,medical_visit_external,dose_external,date);
//
                    davaDaruList.add(davaDaru);
//                    }
                }

                ReportDavaDaruAdapter adapter = new ReportDavaDaruAdapter(ReportDavaDaru.this, davaDaruList);

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

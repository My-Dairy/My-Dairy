package com.example.mydairy.specific;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.mydairy.Details.DailyEntry;
import com.example.mydairy.Details.DanKhanEntry;
import com.example.mydairy.Details.GassCharo;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.GaasCharoAdapter;
import com.example.mydairy.adapter.ReportAdapter;
import com.example.mydairy.adapter.ReportDanKhanAdapter;
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

public class ReportDanKhan extends AppCompatActivity {

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<DanKhanEntry> dankhanList;
    float f1,f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_dan_khan);

        listView = (ListView) findViewById(R.id.dan_khan_recycler);
        dankhanList = new ArrayList<>();
        loaddata();

    }

    private void loaddata() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DanKhan");

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


                    String  pashudan1= snapshot.child("pashudan_Dairy").getValue().toString();
                    String  pashudan2= snapshot.child("pashudan_External").getValue().toString();
                    if((pashudan1==null || pashudan1.isEmpty()) && (pashudan2==null || pashudan2.isEmpty()))
                    {
                        pashudan1="0";
                        pashudan2="0";
                    }
                    else if(pashudan1.isEmpty() || pashudan1==null)
                    {
                        pashudan1="0.0";
                    }
                    else if(pashudan2==null || pashudan2.isEmpty())
                    {
                        pashudan2="0.0";
                    }

                    f1=Float.parseFloat(pashudan1);
                    f2=Float.parseFloat(pashudan2);
                    pashudan1= String.valueOf(f1+f2);
                    //else continue;
                    System.out.println("Jinil pashudan"+pashudan1);

                    String  makaibhardo1= snapshot.child("makai_Own").getValue().toString();
                    String  makaibhardo2= snapshot.child("makai_External").getValue().toString();
                    if((makaibhardo1==null || makaibhardo1.isEmpty()) && (makaibhardo2==null || makaibhardo2.isEmpty()))
                    {
                        makaibhardo1="0";
                        makaibhardo2="0";
                    }
                    else if(makaibhardo1.isEmpty() || makaibhardo1==null)
                    {
                        makaibhardo1="0.0";
                    }
                    else if(makaibhardo2==null || makaibhardo2.isEmpty())
                    {
                        makaibhardo2="0.0";
                    }

                    f1=Float.parseFloat(makaibhardo1);
                    f2=Float.parseFloat(makaibhardo2);
                    makaibhardo1= String.valueOf(f1+f2);
                    //else continue;
                    System.out.println("Jinil makaibhardo"+makaibhardo1);

                    String papdi = snapshot.child("papdi_External").getValue().toString();
                    if(papdi==null)
                    {
                        papdi="0";
                    }
                    //else continue;
                    System.out.println("Jinil papdi"+papdi);

                    String pashuahar = snapshot.child("pashuahar_Details").getValue().toString();
                    if(pashuahar==null)
                    {
                        pashuahar="0";
                    }
                    //else continue;
                    System.out.println("Jinil pashuahar"+pashuahar);

                    String pashuaharAmt = snapshot.child("pashushar_Amount").getValue().toString();
                    if(pashuaharAmt==null)
                    {
                        pashuaharAmt="0";
                    }
                    //else continue;
                    System.out.println("Jinil pashuaharAmt"+pashuaharAmt);


//                    for(DataSnapshot snapshot1:snapshot.getChil dren())
//                    {
//                        System.out.println("JK: "+snapshot1);
////                        //String time = snapshot1.getKey();
//                        if(snapshot1.getKey().equals("checkbox"))
//                        {
//
//                        }
//
//                        String fertilizers = snapshot1.child("fertilizers").getValue().toString();
//                        String labourCharge = snapshot1.child("labourCharge").getValue().toString();
////                        String lilocharoSeeds = snapshot1.child("lilocharoSeeds").getValue().toString();
////                        String lilocharofertilizers = snapshot1.child("lilocharo_fertilizers").getValue().toString();
//                        String seeds = snapshot1.child("seeds").getValue().toString();
//                        String sukocharoAmount = snapshot1.child("sukocharoAmount").getValue().toString();
//                        String sukocharoQuantity = snapshot1.child("sukocharoQuantity").getValue().toString();
//                        String tractorCharge = snapshot1.child("tractorCharge").getValue().toString();
//
//
////                        String Fat = snapshot1.child("fat").getValue().toString();
////                        String Qty = snapshot1.child("quantity").getValue().toString();
////                        String Amt = snapshot1.child("amount").getValue().toString();
//
                    DanKhanEntry danKhanEntry = new DanKhanEntry(pashudan1, makaibhardo1, papdi, pashuahar, pashuaharAmt, date);
                    dankhanList.add(danKhanEntry);
//                    }
                }

                ReportDanKhanAdapter adapter = new ReportDanKhanAdapter(ReportDanKhan.this, dankhanList);

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

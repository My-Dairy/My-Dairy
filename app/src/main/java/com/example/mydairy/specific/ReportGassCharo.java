package com.example.mydairy.specific;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.mydairy.Details.DailyEntry;
import com.example.mydairy.Details.GassCharo;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.GaasCharoAdapter;
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
public class ReportGassCharo extends AppCompatActivity
{
    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<GassCharo> gaasCharoList;
    float f1,f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_gass_charo);

        listView = (ListView) findViewById(R.id.gaas_charo_recycler);
        gaasCharoList = new ArrayList<>();
        loaddata();

    }

    public void loaddata()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("GassCharo");

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

                    String checkbox1 = snapshot.child("checkbox").getValue().toString();
                    System.out.println("Jinil checkbox1 "+checkbox1);
                    String checkbox="";
                    if(checkbox1.equals("00000"))
                    {
                        checkbox="None";
                    }
                    for(int i=0;i<checkbox1.length();i++)
                    {
                        if(checkbox1.charAt(i)=='1' && i==0)
                        {
                            checkbox+="Guno ";
                        }
                        else if(checkbox1.charAt(i)=='1' && i==1)
                        {
                            checkbox+="Makaiyu ";
                        }
                        else if(checkbox1.charAt(i)=='1' && i==2)
                        {
                            checkbox+="Guvariyu ";
                        }
                        else if(checkbox1.charAt(i)=='1' && i==3)
                        {
                            checkbox+="Sidhiyu ";
                        }
                        else if(checkbox1.charAt(i)=='1' && i==4)
                        {
                            checkbox+="other";
                        }
                        //checkbox+=",";
                    }
                    checkbox+=".";
                    //else continue;
                    System.out.println(checkbox+checkbox1);
                    System.out.println("Jinil checkbox "+checkbox);

                    String  sukocharoQuantity= snapshot.child("sukhocharoQuantity").getValue().toString();
                    if(sukocharoQuantity=="")
                    {
                        sukocharoQuantity="0";
                    }
                    //else continue;
                    System.out.println("Jinil sukocharoquant"+sukocharoQuantity);

                    String sukocharoAmount = snapshot.child("sukhocharoAmount").getValue().toString();
                    if(sukocharoAmount=="")
                    {
                        sukocharoAmount="0";
                    }
                    //else continue;
                    System.out.println("Jinil sukocharoamt"+sukocharoAmount);

                    String tractorCharge = snapshot.child("tractorCharge").getValue().toString();
                    if(tractorCharge==null)
                    {
                        tractorCharge="0.0";
                    }
                    //else continue;
                    System.out.println("Jinil tractor"+tractorCharge);

                    String seeds = snapshot.child("seeds").getValue().toString();
                    String lilocharoSeeds = snapshot.child("lilocharoSeeds").getValue().toString();

                    if((seeds==null || seeds.isEmpty()) && (lilocharoSeeds==null || lilocharoSeeds.isEmpty()))
                    {
                        seeds="0.0";
                        lilocharoSeeds="0.0";
                    }
                    else if(seeds.isEmpty() || seeds==null)
                    {
                        seeds="0.0";
                    }
                    else if(lilocharoSeeds==null || lilocharoSeeds.isEmpty())
                    {
                        lilocharoSeeds="0.0";
                    }

                    f1=Float.parseFloat(seeds);
                    f2=Float.parseFloat(lilocharoSeeds);
                    seeds= String.valueOf(f1+f2);
                    System.out.println("Jinil seed"+seeds);

                    String fertilizers = snapshot.child("fertilizers").getValue().toString();
                    String lilocharofertilizers = snapshot.child("lilocharo_fertilizers").getValue().toString();

                    if((fertilizers==null || fertilizers.isEmpty()) && (lilocharofertilizers==null || lilocharofertilizers.isEmpty()))
                    {
                        fertilizers="0.0";
                        lilocharofertilizers="0.0";
                    }
                    else if(fertilizers==null || fertilizers.isEmpty())
                    {
                        fertilizers="0.0";
                    }
                    else if(lilocharofertilizers==null || lilocharofertilizers.isEmpty())
                    {
                        lilocharofertilizers="0.0";
                    }

                    f1=Float.parseFloat(fertilizers);
                    f2=Float.parseFloat(lilocharofertilizers);
                    fertilizers= String.valueOf(f1+f2);
                    System.out.println("Jinil ferti"+fertilizers);

                    String labourCharge = snapshot.child("labourCharge").getValue().toString();
                    if(labourCharge==null)
                    {
                        labourCharge="0.0";
                    }
                    //else continue;
                    System.out.println("Jinil labour"+labourCharge);







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
                    GassCharo gassCharo = new GassCharo(checkbox,sukocharoQuantity,sukocharoAmount,tractorCharge,seeds,fertilizers,labourCharge,date);
//
                    gaasCharoList.add(gassCharo);
//                    }
                }

                GaasCharoAdapter adapter = new GaasCharoAdapter(ReportGassCharo.this, gaasCharoList);

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

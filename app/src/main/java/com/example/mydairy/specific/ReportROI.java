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
import com.example.mydairy.Details.ROI;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.GaasCharoAdapter;
import com.example.mydairy.adapter.ReportAdapter;
import com.example.mydairy.adapter.ReportDanKhanAdapter;
import com.example.mydairy.adapter.ReportDavaDaruAdapter;
import com.example.mydairy.adapter.ReportMilkBillAdapter;
import com.example.mydairy.adapter.ReportRoiAdapter;
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

public class ReportROI extends AppCompatActivity{

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<ROI> roiList;
    float f1,f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_roi);

        listView = (ListView) findViewById(R.id.roi_recycler);
        roiList = new ArrayList<>();
        loaddata();

    }

    private void loaddata() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("ROI");

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


                    String roi_balance= snapshot.child("roi_balance").getValue().toString();
                    if(roi_balance=="")
                    {
                        roi_balance="0";
                    }
                    //else continue;
                    System.out.println("Jinil roi_balance"+roi_balance);

                    String roi_monthly= snapshot.child("roi_monthly").getValue().toString();
                    if(roi_monthly=="")
                    {
                        roi_monthly="0";
                    }
                    //else continue;
                    System.out.println("Jinil roi_monthly"+roi_monthly);

                    String roi_withdraw= snapshot.child("roi_monthly").getValue().toString();
                    if(roi_withdraw=="")
                    {
                        roi_withdraw="0";
                    }
                    //else continue;
                    System.out.println("Jinil roi_withdraw"+roi_withdraw);


                    ROI roi = new ROI(roi_monthly,roi_withdraw,roi_balance,date);
//
                    roiList.add(roi);
//                    }
                }

                ReportRoiAdapter adapter = new ReportRoiAdapter(ReportROI.this, roiList);

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

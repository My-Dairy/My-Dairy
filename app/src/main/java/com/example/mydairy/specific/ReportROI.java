package com.example.mydairy.specific;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class ReportROI extends AppCompatActivity{

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    Calendar myCalendar, myCalendar1;
    EditText startdate, enddate;
    ArrayList<ROI> roiList;
    float f1,f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_roi);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color, this.getTheme()));
        }

        listView = (ListView) findViewById(R.id.roi_recycler);
        roiList = new ArrayList<>();
        loadalldata();

        myCalendar = Calendar.getInstance();
        startdate = (EditText) findViewById(R.id.start_dte_edit);
        DatePickerDialog.OnDateSetListener date  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateStartLabel();
            }
        };
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportROI.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        myCalendar1 = Calendar.getInstance();
        enddate = (EditText) findViewById(R.id.end_dte_edit);
        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, month);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEndLabel();
            }
        };

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportROI.this, date1, myCalendar1
                        .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        Button Search = (Button) findViewById(R.id.search_btn);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loadspecificdata();
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("SPPatel");
                }
            }
        });

        Button Reset = (Button) findViewById(R.id.reset_btn);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadalldata();
            }
        });

    }

    private void updateStartLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startdate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateEndLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        enddate.setText(sdf.format(myCalendar1.getTime()));
    }

    public void loadspecificdata() throws ParseException {

        roiList.clear();
        listView.setAdapter(null);

        EditText Min = (EditText) findViewById(R.id.start_dte_edit);
        String mindate = Min.getText().toString();
        Date datemin = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(mindate);
        System.out.println("Sarthak: "+mindate);

        EditText Max = (EditText) findViewById(R.id.end_dte_edit);
        String maxdate = Max.getText().toString();
        Date datemax = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(maxdate);
        System.out.println("Sarthak: "+datemax);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("ROI");

        mPostReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String date = snapshot.getKey();
                    date = date.replace("-","/");

                    date = date.substring(0,10);

                    try {

                        Date date1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(date);
                        System.out.println("ooooo"+date);
                        System.out.println("ooooo"+date1);
                        System.out.println("ooooo"+datemin);
                        System.out.println("ooooo"+datemax);
                        if((date1.compareTo(datemin)>=0)&&(date1.compareTo(datemax)<=0))
                        {
                            System.out.println("aaaaaaaa :");
                           // for(DataSnapshot snapshot1:snapshot.getChildren())


                                System.out.println("Dateee:"+snapshot);
                                String s1 = snapshot.child("roi_monthly").getValue().toString();
                                String s2 = snapshot.child("roi_withdraw").getValue().toString();
                                String s3 = snapshot.child("roi_balance").getValue().toString();
                                System.out.println("aaaaaaaaa:"+date);
                                ROI roi = new ROI(s1,s2,s3,date);

                                roiList.add(roi);

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("SPPatel");
                    }
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
    private void loadalldata() {

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

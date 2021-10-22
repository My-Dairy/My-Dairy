package com.example.mydairy.specific;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mydairy.Details.DailyEntry;
import com.example.mydairy.Details.DanKhanEntry;
import com.example.mydairy.Details.GassCharo;
import com.example.mydairy.Details.Misc;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.GaasCharoAdapter;
import com.example.mydairy.adapter.ReportAdapter;
import com.example.mydairy.adapter.ReportDanKhanAdapter;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class ReportDanKhan extends AppCompatActivity {

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<DanKhanEntry> dankhanList;
    Calendar myCalendar, myCalendar1;
    EditText startdate, enddate;
    float f1,f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_dan_khan);

        listView = (ListView) findViewById(R.id.dan_khan_recycler);
        dankhanList = new ArrayList<>();
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportDanKhan.this, date, myCalendar
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportDanKhan.this, date1, myCalendar1
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
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startdate.setText(sdf.format(myCalendar.getTime()));
    }
    public void loadspecificdata() throws ParseException {

        dankhanList.clear();
        listView.setAdapter(null);

        EditText Min = (EditText) findViewById(R.id.start_dte_edit);
        String mindate = Min.getText().toString();
        Date datemin = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(mindate);
        System.out.println("Sarthak: "+mindate);

        EditText Max = (EditText) findViewById(R.id.end_dte_edit);
        String maxdate = Max.getText().toString();
        Date datemax = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(maxdate);
        System.out.println("Sarthak: "+datemax);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DanKhan");

        mPostReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String date = snapshot.getKey();
                    date = date.replace("-","/");

                    date = date.substring(0,10);

                    try {

                        Date date1 = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(date);
                        System.out.println("ooooo"+date);
                        System.out.println("ooooo"+date1);
                        System.out.println("ooooo"+datemin);
                        System.out.println("ooooo"+datemax);
                        if((date1.compareTo(datemin)>=0)&&(date1.compareTo(datemax)<=0))
                        {
                            System.out.println("aaaaaaaa :");
                            // for(DataSnapshot snapshot1:snapshot.getChildren())

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



                            System.out.println("aaaaaaaaa:"+date);


                            DanKhanEntry danKhanEntry = new DanKhanEntry(pashudan1, makaibhardo1, papdi, pashuahar, pashuaharAmt, date);
                            dankhanList.add(danKhanEntry);


                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("SPPatel");
                    }

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

    private void updateEndLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        enddate.setText(sdf.format(myCalendar1.getTime()));
    }

    private void loadalldata() {

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

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
import com.google.firebase.database.core.Repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;


public class ReportDavaDaru extends AppCompatActivity{

    ListView listView;
    EditText startdate, enddate;
    Calendar myCalendar, myCalendar1;
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
        loadalldata();

        //Setting the datepicker.
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportDavaDaru.this, date, myCalendar
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportDavaDaru.this, date1, myCalendar1
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

    private void loadalldata() {

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
        davaDaruList.clear();
        listView.setAdapter(null);

        EditText Min = (EditText) findViewById(R.id.start_dte_edit);
        String mindate = Min.getText().toString();
        Date datemin = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(mindate);
        System.out.println("SPPatel: "+mindate);

        EditText Max = (EditText) findViewById(R.id.end_dte_edit);
        String maxdate = Max.getText().toString();
        Date datemax = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(maxdate);
        System.out.println("SPPatel: "+maxdate);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DavaDaru");

        mPostReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String date = snapshot.getKey();
                    date = date.replace("-","/");

                    date = date.substring(0,10);
                    try {
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(date);
                        if ((date1.compareTo(datemin) >= 0) && (date1.compareTo(datemax) <= 0)) {


                            String medicine_external = snapshot.child("medicine_external").getValue().toString();
                            if (medicine_external == "") {
                                medicine_external = "0";
                            }
                            //else continue;
                            System.out.println("Jinil medicine" + medicine_external);


                            String medical_visit_external = snapshot.child("medical_visit_external").getValue().toString();
                            String medical_visit_dairy = snapshot.child("medical_visit_dairy").getValue().toString();

                            if ((medical_visit_external == null || medical_visit_external.isEmpty()) && (medical_visit_dairy == null || medical_visit_dairy.isEmpty())) {
                                medical_visit_external = "0.0";
                                medical_visit_dairy = "0.0";
                            } else if (medical_visit_external.isEmpty() || medical_visit_external == null) {
                                medical_visit_external = "0.0";
                            } else if (medical_visit_dairy == null || medical_visit_dairy.isEmpty()) {
                                medical_visit_dairy = "0.0";
                            }

                            f1 = Float.parseFloat(medical_visit_external);
                            f2 = Float.parseFloat(medical_visit_dairy);
                            medical_visit_external = String.valueOf(f1 + f2);
                            System.out.println("Jinil medical external " + f1 + " " + f2 + " " + medical_visit_external);

                            String dose_dairy = snapshot.child("dose_dairy").getValue().toString();
                            String dose_external = snapshot.child("dose_external").getValue().toString();

                            if ((dose_dairy == null || dose_dairy.isEmpty()) && (dose_external == null || dose_external.isEmpty())) {
                                dose_external = "0.0";
                                dose_dairy = "0.0";
                            } else if (dose_dairy == null || dose_dairy.isEmpty()) {
                                dose_dairy = "0.0";
                            } else if (dose_external == null || dose_external.isEmpty()) {
                                dose_external = "0.0";
                            }

                            f1 = Float.parseFloat(dose_dairy);
                            f2 = Float.parseFloat(dose_external);
                            dose_external = String.valueOf(f1 + f2);
                            System.out.println("Jinil dose " + f1 + " " + f2 + " " + dose_external);

                            DavaDaru davaDaru = new DavaDaru(medicine_external, medical_visit_external, dose_external, date);
//
                            davaDaruList.add(davaDaru);


                        }



                        }
                     catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("SPPatel");
                    }
                }

                ReportDavaDaruAdapter adapter = new ReportDavaDaruAdapter(ReportDavaDaru.this,davaDaruList);

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

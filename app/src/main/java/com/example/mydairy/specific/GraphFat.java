package com.example.mydairy.specific;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydairy.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GraphFat extends AppCompatActivity {

    private LineChart mChart,mChart2;
    private DatabaseReference mPostReference,mPostReference2;
    Calendar myCalendar, myCalendar1;
    EditText startdate, enddate;
    ValueEventListener valueEventListener;
    ArrayList<Entry> yData_m,yData_e;
    ArrayList<String> xData_m,xData_e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fat_graph);

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(GraphFat.this, date, myCalendar
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(GraphFat.this, date1, myCalendar1
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

//        Button Reset = (Button) findViewById(R.id.reset_btn);
//
//        Reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadalldata();
//            }
//
//
//        });

    }

    private void loadspecificdata() throws ParseException{

        // First Chart.
        mChart = (LineChart)findViewById(R.id.specific_graph);
        mChart.animateX(1000);

        EditText Min = (EditText) findViewById(R.id.start_dte_edit);
        String mindate = Min.getText().toString();
        Date datemin = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(mindate);
        System.out.println("SPPatel: "+mindate);

        EditText Max = (EditText) findViewById(R.id.end_dte_edit);
        String maxdate = Max.getText().toString();
        Date datemax = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(maxdate);
        System.out.println("SPPatel: "+maxdate);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();
        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DailyEntry");
        mPostReference.addListenerForSingleValueEvent(valueEventListener= new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {

                yData_m = new ArrayList<>();
                xData_m = new ArrayList<String>();

                float i =0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String date = ds.getKey();
                    date = date.replace("-","/");

                    try {
                        Date date1 = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(date);
                        if((date1.compareTo(datemin)>=0)&&(date1.compareTo(datemax)<=0)) {
                            for (DataSnapshot dataSnapshot1 : ds.getChildren()) {
                                if (dataSnapshot1.getKey().toString().equals("morning")) {
                                    String SV = ds.child("morning").child("fat").getValue().toString();
                                    Float SensorValue = Float.parseFloat(SV);

                                    i = i + 1;

                                    yData_m.add(new Entry(i, SensorValue));
                                    xData_m.add(date);
                                }
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

                final LineDataSet lineDataSet = new LineDataSet(yData_m,"Fat Morning");
                LineData data = new LineData(lineDataSet);
                XAxis xaxis =mChart.getXAxis();
                xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xaxis.setCenterAxisLabels(false);
                YAxis yAxisRight = mChart.getAxisRight();
                yAxisRight.setEnabled(false);
                xaxis.setDrawGridLines(true);
                xaxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return xData_m.get((int) value % xData_m.size());
                    }
                });

                if (xData_m.isEmpty()||xData_m.size()==1) {
                    mChart.clear();
                } else {
                    // set data
                    mChart.setData(data);
                    mChart.setBackgroundColor(Color.rgb(244, 117, 117));
                }
                mChart.getXAxis().setGranularityEnabled(true);
                mChart.setNoDataText("Sorry! No Data Found");
                mChart.notifyDataSetChanged();
                mChart.invalidate();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.i("Graph 1 Error: ",error.getMessage());
            }
        });

        //Second Chart
        mChart2 = (LineChart)findViewById(R.id.specific_graph1);
        mChart2.animateX(1000);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String keyid = user.getUid();

        mPostReference2 = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DailyEntry");
        mPostReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                yData_e = new ArrayList<>();
                xData_e = new ArrayList<String>();

                float i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String date = ds.getKey();
                    date = date.replace("-","/");
                    try {
                        Date date1 = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(date);
                        if ((date1.compareTo(datemin) >= 0) && (date1.compareTo(datemax) <= 0)) {

                            for (DataSnapshot dataSnapshot1 : ds.getChildren()) {
                                if (dataSnapshot1.getKey().toString().equals("evening")) {
                                    String SV = ds.child("evening").child("fat").getValue().toString();
                                    Float SensorValue = Float.parseFloat(SV);

                                    i = i + 1;

                                    yData_e.add(new Entry(i, SensorValue));
                                    xData_e.add(date);
                                }
                            }
                        }
                    }catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                final LineDataSet lineDataSet = new LineDataSet(yData_e,"Fat Evening");
                LineData data = new LineData(lineDataSet);
                XAxis xaxis =mChart2.getXAxis();
                xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xaxis.setCenterAxisLabels(false);
                YAxis yAxisRight = mChart2.getAxisRight();
                yAxisRight.setEnabled(false);
                xaxis.setDrawGridLines(true);
                xaxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return xData_e.get((int) value % xData_e.size());
                    }
                });

                if (xData_e.isEmpty()||xData_e.size()==1) {
                    mChart2.clear();
                } else {
                    // set data
                    mChart2.setData(data);
                    mChart2.setBackgroundColor(Color.rgb(244, 117, 117));
                }

                mChart2.getXAxis().setGranularityEnabled(true);
                mChart2.setNoDataText("Sorry! No Data Found");
                mChart2.notifyDataSetChanged();
                mChart2.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Graph 2 Error: ",error.getMessage());
            }
        });


    }


    private void updateEndLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        enddate.setText(sdf.format(myCalendar1.getTime()));
    }

    private void updateStartLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startdate.setText(sdf.format(myCalendar.getTime()));
    }
}

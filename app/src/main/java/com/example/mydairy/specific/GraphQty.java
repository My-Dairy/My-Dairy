package com.example.mydairy.specific;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

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

import java.util.ArrayList;

public class GraphQty extends AppCompatActivity {

    private LineChart mChart,mChart2;
    private DatabaseReference mPostReference,mPostReference2;
    ValueEventListener valueEventListener;
    ArrayList<Entry> yData_m,yData_e;
    ArrayList<String> xData_m,xData_e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_qty);

        // First Chart.
        mChart = (LineChart)findViewById(R.id.specific_graph);
        mChart.animateX(1000);
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

                    for(DataSnapshot dataSnapshot1: ds.getChildren())
                    {
                        if(dataSnapshot1.getKey().toString().equals("morning"))
                        {
                            String SV = ds.child("morning").child("quantity").getValue().toString();
                            Float SensorValue = Float.parseFloat(SV);

                            i=i+1;

                            yData_m.add(new Entry(i,SensorValue));
                            xData_m.add(ds.getKey());
                        }
                    }
                }

                final LineDataSet lineDataSet = new LineDataSet(yData_m,"Qty Morning");
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
                        return xData_m.get((int) value - 1);
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


        // Second Chart.
        mChart2 = (LineChart)findViewById(R.id.specific_graph1);
        mChart2.animateX(1000);

        mPostReference2 = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DailyEntry");
        mPostReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                yData_e = new ArrayList<>();
                xData_e = new ArrayList<String>();

                float i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    for(DataSnapshot dataSnapshot1: ds.getChildren())
                    {
                        if(dataSnapshot1.getKey().toString().equals("evening"))
                        {
                            String SV = ds.child("evening").child("quantity").getValue().toString();
                            Float SensorValue = Float.parseFloat(SV);

                            i=i+1;

                            yData_e.add(new Entry(i,SensorValue));
                            xData_e.add(ds.getKey());
                        }
                    }
                }

                final LineDataSet lineDataSet = new LineDataSet(yData_e,"Qty Evening");
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
                        return xData_e.get((int) value - 1);
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
}
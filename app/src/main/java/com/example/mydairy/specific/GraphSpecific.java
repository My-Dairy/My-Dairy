package com.example.mydairy.specific;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

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
import com.google.android.material.slider.LabelFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GraphSpecific extends AppCompatActivity {

    private LineChart mChart;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ValueEventListener valueEventListener;
    ArrayList<Entry> yData;
    ArrayList<String> xData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_graph);

        mChart = (LineChart)findViewById(R.id.specific_graph);
        mChart.animateX(1000);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();
        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DailyEntry");
        mPostReference.addValueEventListener(valueEventListener= new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                yData = new ArrayList<>();
                xData = new ArrayList<String>();
                //String xValues[]={"first", "second", "third"}
                List<String> xVal=new ArrayList<String>();
                List axisVal=new ArrayList();
//                xVal.add(dataSnapshot.getValue().toString());
//                int k;
//                for(k=0;k<xVal.size();k++) {
//                    Log.i("Jeet", String.valueOf(xVal));
//                    Log.i("seize", String.valueOf(xVal.size()));
//                }


                float i =0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    i=i+1;

                    String SV = ds.child("evening").child("fat").getValue().toString();
                    Float SensorValue = Float.parseFloat(SV);
                    Log.i("Meet : ",ds.getKey());
//                    Log.i("Jeet",SensorValue.toString());
//                    String XSV = ds.getValue().toString();
//                    Float XSensorValue = Float.parseFloat(XSV);
                    yData.add(new Entry(i,SensorValue));
                    xData.add(ds.getKey());


//                    xData.add(new Entry(i,XSensorValue));
                }

//                XAxis xAxis = mChart.getXAxis();
//                xAxis.setGranularity(1f);
//                xAxis.setGranularityEnabled(true);

                final LineDataSet lineDataSet = new LineDataSet(yData,"Fat");
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

//                        Log.i("Heet", String.valueOf(value));
//                        Log.i("jheet", String.valueOf(xData.size()));
                        return xData.get((int) value - 1);
                    }
                });
                mChart.setData(data);
                mChart.setBackgroundColor(Color.rgb(244, 117, 117));
                mChart.getXAxis().setGranularityEnabled(true);
                mChart.setNoDataText("Wait for sometime");
                mChart.notifyDataSetChanged();
                mChart.invalidate();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    Log.i("SNT : ",error.getMessage());
            }
        });



    }


//    private void setData(int count, int range){
//        ArrayList<Entry> yVals1 = new ArrayList<>();
//        for(int i=0;i<count;i++)
//        {
//            float val = (float) (Math.random()*range)+200;
//            yVals1.add(new Entry(i,val));
//        }
//
//        ArrayList<Entry> yVals2 = new ArrayList<>();
//        for(int i=0;i<count;i++)
//        {
//            float val = (float) (Math.random()*range)+100;
//            yVals2.add(new Entry(i,val));
//        }
//
//
//
//        LineDataSet set1,set2,set3;
//        set1 = new LineDataSet(yVals1,"Morning");
//        set1.setColor(Color.RED);
//        set1.setLineWidth(3f);
//        set1.setValueTextSize(10f);
//
//        set2 = new LineDataSet(yVals2,"Evening");
//        set2.setColor(Color.BLUE);
//        set1.setLineWidth(3f);
//        set1.setValueTextSize(10f);
//
//
//        LineData data = new LineData(set1,set2);
//        mChart.setData(data);
//    }
}

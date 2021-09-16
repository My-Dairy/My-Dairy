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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GraphFat extends AppCompatActivity {

    private LineChart mChart,mChart2;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference,mPostReference2;
    ValueEventListener valueEventListener,valueEventListener2;
    ArrayList<Entry> yData1,yData2;
    ArrayList<String> xData1,xData2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fat_graph);

        mChart = (LineChart)findViewById(R.id.specific_graph);
        mChart.animateX(1000);
        mChart2 = (LineChart)findViewById(R.id.specific_graph1);
        mChart2.animateX(1000);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();
        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DailyEntry");
        mPostReference.addListenerForSingleValueEvent(valueEventListener= new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {

                yData1 = new ArrayList<>();
                yData2 = new ArrayList<>();
                xData1 = new ArrayList<String>();
                xData2 = new ArrayList<String>();

                float i =0,j = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    i=i+1;

                    String SV1 = ds.child("morning").child("fat").getValue().toString();
                    Float SensorValue1 = Float.parseFloat(SV1);

                    yData1.add(new Entry(i,SensorValue1));
                    xData1.add(ds.getKey());

                }
                i=0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    i=i+1;

                    String SV2 = ds.child("evening").child("fat").getValue().toString();
                    if(SV2=="")
                    {

                    }
                    else{
                        Float SensorValue2 = Float.parseFloat(SV2);

                        yData2.add(new Entry(i,SensorValue2));
                        xData2.add(ds.getKey());
                        Log.i("Jinil",ds.getKey());}

                }

                final LineDataSet lineDataSet;
                lineDataSet= new LineDataSet(yData1,"Morning Fat");
                lineDataSet.setColor(Color.BLUE);
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

                        return xData1.get((int) value - 1);
                    }
                });

                if (xData1.isEmpty()||xData1.size()==1) {
                    mChart.clear();
                } else {
                    // set data
                    mChart.setData(data);
                    //mChart.setBackgroundColor(Color.rgb(244, 117, 117));
                }
                mChart.getXAxis().setGranularityEnabled(true);
                mChart.setNoDataText("Please add atleast 2 Entries");
                mChart.setDrawBorders(true);
                mChart.notifyDataSetChanged();
                mChart.invalidate();

                final LineDataSet lineDataSet1;
                lineDataSet1= new LineDataSet(yData2,"Evening Fat");
                lineDataSet1.setColor(Color.BLACK);
                LineData data1 = new LineData(lineDataSet1);
                XAxis xaxis1 =mChart2.getXAxis();
                xaxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
                xaxis1.setCenterAxisLabels(false);
                YAxis yAxisRight1 = mChart2.getAxisRight();
                yAxisRight1.setEnabled(false);
                xaxis1.setDrawGridLines(true);
                xaxis1.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {

                        return xData2.get((int) value - 1);
                    }
                });

                if (xData2.isEmpty()||xData2.size()==1) {
                    mChart2.clear();
                } else {
                    // set data
                    mChart2.setData(data1);
                    //mChart.setBackgroundColor(Color.rgb(244, 117, 117));
                }
                mChart2.getXAxis().setGranularityEnabled(true);
                mChart2.setNoDataText("Please add atleast 2 Entries");
                mChart2.setDrawBorders(true);
                mChart2.notifyDataSetChanged();
                mChart2.invalidate();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.i("Graph Error: ",error.getMessage());
            }
        });



    }
}

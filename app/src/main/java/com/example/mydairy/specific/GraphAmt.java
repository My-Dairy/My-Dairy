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

public class GraphAmt extends AppCompatActivity {

    private LineChart mChart;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ValueEventListener valueEventListener;
    ArrayList<Entry> yData,yData_new;
    ArrayList<String> xData,xData_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_amt);


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

                float i =0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    i=i+1;

                    String SV = ds.child("evening").child("amount").getValue().toString();
                    Float SensorValue = Float.parseFloat(SV);

                    yData.add(new Entry(i,SensorValue));
                    xData.add(ds.getKey());

                }

                yData_new = new ArrayList<>();
                xData_new = new ArrayList<String>();

                for(int x=0;x<xData.size();x++)
                {

                }

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
                        return xData.get((int) value - 1);
                    }
                });

                if (xData.isEmpty()||xData.size()==1) {
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
                Log.i("Graph Error: ",error.getMessage());
            }
        });
    }
}
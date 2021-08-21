package com.example.mydairy.specific;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydairy.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class GraphSpecific extends AppCompatActivity {

    private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_graph);

        mChart = (LineChart)findViewById(R.id.specific_graph);
        setData(10,60);
        mChart.animateX(1000);


    }

    private void setData(int count, int range){
        ArrayList<Entry> yVals1 = new ArrayList<>();
        for(int i=0;i<count;i++)
        {
            float val = (float) (Math.random()*range)+200;
            yVals1.add(new Entry(i,val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();
        for(int i=0;i<count;i++)
        {
            float val = (float) (Math.random()*range)+100;
            yVals2.add(new Entry(i,val));
        }



        LineDataSet set1,set2,set3;
        set1 = new LineDataSet(yVals1,"Morning");
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(10f);

        set2 = new LineDataSet(yVals2,"Evening");
        set2.setColor(Color.BLUE);
        set1.setLineWidth(3f);
        set1.setValueTextSize(10f);


        LineData data = new LineData(set1,set2);
        mChart.setData(data);
    }
}

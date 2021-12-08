package com.example.mydairy.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydairy.R;

public class ReportAdapter extends ArrayAdapter {

    private String[] Title_Report;
    private Integer[] Image_Report;
    private Activity context;

    public ReportAdapter(Activity context, String[] title_Report, Integer[] image_Report) {
        super(context, R.layout.activity_report_adapter, title_Report);
        this.context = context;
        this.Title_Report = title_Report;
        this.Image_Report = image_Report;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();

        if(convertView==null)
            row = inflater.inflate(R.layout.activity_report_adapter, parent, false);

        TextView ReportTitle = (TextView) row.findViewById(R.id.list_reporttxt);
        ImageView ReportImage = (ImageView) row.findViewById(R.id.list_reportimg);

        ReportTitle.setText(Title_Report[position]);
        ReportImage.setImageResource(Image_Report[position]);
        return row;
    }
}
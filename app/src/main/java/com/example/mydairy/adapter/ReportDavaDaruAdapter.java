package com.example.mydairy.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mydairy.Details.DailyEntry;
import com.example.mydairy.Details.DavaDaru;
import com.example.mydairy.R;

import java.util.ArrayList;
import java.util.List;
public class ReportDavaDaruAdapter extends ArrayAdapter<DavaDaru>{

    List<DavaDaru> davaDaruList;
    public ReportDavaDaruAdapter(@NonNull Activity context, ArrayList<DavaDaru> davaDaruArrayList) {
        super(context, R.layout.activity_report_dava_daru, davaDaruArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.table_dava_daru, parent, false);
        }

        TextView date = itemView.findViewById(R.id.table_date);
        TextView medicine = itemView.findViewById(R.id.table_medicine);
        TextView medicalVisit = itemView.findViewById(R.id.table_medical_visit);
        TextView dose = itemView.findViewById(R.id.table_dose);



        DavaDaru davaDaru = getItem(position);


        date.setText(davaDaru.getDate());
        medicine.setText(davaDaru.getMedicine_external());
        medicalVisit.setText(davaDaru.getMedical_visit_external());
        dose.setText(davaDaru.getDose_external());
//        amt.setText(dailyEntry.getAmount());

        return itemView;
    }


}

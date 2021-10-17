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
import com.example.mydairy.Details.Misc;
import com.example.mydairy.R;

import java.util.ArrayList;
import java.util.List;
public class ReportMiscAdapter extends ArrayAdapter<Misc>{


    List<Misc> miscList;
    public ReportMiscAdapter(@NonNull Activity context, ArrayList<Misc> miscList) {
        super(context, R.layout.activity_report_misc, miscList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.table_misc, parent, false);
        }

        TextView date = itemView.findViewById(R.id.table_date);
        TextView electricityBill = itemView.findViewById(R.id.table_electricity_bill);
        TextView gasBill = itemView.findViewById(R.id.table_gas_bottle);
        TextView maintenanceAmount = itemView.findViewById(R.id.table_maintenance_amount);
        TextView maintenanceDetails = itemView.findViewById(R.id.table_maintenance_name);
        TextView otherAmount = itemView.findViewById(R.id.table_other_amount);
        TextView otherDetail = itemView.findViewById(R.id.table_other_work);



        Misc misc = getItem(position);


        date.setText(misc.getDate());
        electricityBill.setText(misc.getElectricityBill());
        gasBill.setText(misc.getGasBill());
        maintenanceAmount.setText(misc.getMaintenanceAmount());
        maintenanceDetails.setText(misc.getMaintenanceDetails());
        otherAmount.setText(misc.getOtherAmount());
        otherDetail.setText(misc.getOtherDetails());
//        amt.setText(dailyEntry.getAmount());

        return itemView;
    }


}

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
import com.example.mydairy.Details.ROI;
import com.example.mydairy.R;

import java.util.ArrayList;
import java.util.List;
public class ReportRoiAdapter extends ArrayAdapter<ROI>{


    List<ROI> roiList;
    public ReportRoiAdapter(@NonNull Activity context, ArrayList<ROI> roiList) {
        super(context, R.layout.activity_report_roi, roiList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.table_roi, parent, false);
        }

        TextView date = itemView.findViewById(R.id.table_date);
        TextView roiMonthly = itemView.findViewById(R.id.table_monthly);
        TextView roiDeposit = itemView.findViewById(R.id.table_deposit);
        TextView roiWithdraw = itemView.findViewById(R.id.table_withdraw);



        ROI roi = getItem(position);


        date.setText(roi.getDate());
        roiMonthly.setText(roi.getRoi_monthly());
        roiWithdraw.setText(roi.getRoi_withdraw());
        roiDeposit.setText(roi.getRoi_balance());
//        amt.setText(dailyEntry.getAmount());

        return itemView;
    }

}

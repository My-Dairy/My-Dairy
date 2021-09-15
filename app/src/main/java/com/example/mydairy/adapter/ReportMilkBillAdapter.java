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
import com.example.mydairy.R;

import java.util.ArrayList;
import java.util.List;

public class ReportMilkBillAdapter extends ArrayAdapter<DailyEntry>{

    private TextView Date, Fat, Qty, Amt, Time;
    private Activity mContext;
    List<DailyEntry> dailyEntryList;

    public ReportMilkBillAdapter(@NonNull Activity mContext, ArrayList<DailyEntry> dailyEntryArrayList) {
        super(mContext, R.layout.activity_report_milk_bill, dailyEntryArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.table_milk_bill, parent, false);
        }

        TextView date = itemView.findViewById(R.id.table_date);
        TextView time = itemView.findViewById(R.id.table_time);
        TextView fat = itemView.findViewById(R.id.table_fat);
        TextView qty = itemView.findViewById(R.id.table_qty);
        TextView amt = itemView.findViewById(R.id.table_amt);


        DailyEntry dailyEntry = getItem(position);


        date.setText(dailyEntry.getDate());
        time.setText(dailyEntry.getTime());
        fat.setText(dailyEntry.getFat());
        qty.setText(dailyEntry.getQuantity());
        amt.setText(dailyEntry.getAmount());

        return itemView;
    }


}

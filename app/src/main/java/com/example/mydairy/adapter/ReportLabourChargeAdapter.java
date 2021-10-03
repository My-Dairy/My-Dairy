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
import com.example.mydairy.Details.DanKhanEntry;
import com.example.mydairy.Details.DavaDaru;
import com.example.mydairy.Details.LabourCharge;
import com.example.mydairy.R;

import java.util.ArrayList;
import java.util.List;

public class ReportLabourChargeAdapter extends ArrayAdapter<LabourCharge>{


    public ReportLabourChargeAdapter(@NonNull Activity context, ArrayList<LabourCharge>labourChargeArrayList) {
        super(context, R.layout.report_labour_charge, labourChargeArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.table_labour_charge, parent, false);
        }

        TextView date = itemView.findViewById(R.id.table_date);
        //TextView time = itemView.findViewById(R.id.table_time);
        TextView salary = itemView.findViewById(R.id.table_salary);
        TextView withdraw = itemView.findViewById(R.id.table_withdraw);
        TextView deposit = itemView.findViewById(R.id.table_deposit);
        TextView other_work = itemView.findViewById(R.id.labour_other_work);
        TextView other_work_amt = itemView.findViewById(R.id.labour_other_work_amt);

        LabourCharge labourCharge = getItem(position);


        date.setText(labourCharge.getDate());
        //time.setText(danKhanEntry.getTime());
        salary.setText(labourCharge.getMonthly());

        withdraw.setText(labourCharge.getWithdraw());

        deposit.setText(labourCharge.getBalance());

        other_work.setText(labourCharge.getType());

        other_work_amt.setText(labourCharge.getAmount());

        return itemView;
    }

}

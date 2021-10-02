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
import com.example.mydairy.R;

import java.util.ArrayList;
import java.util.List;
public class ReportDanKhanAdapter extends ArrayAdapter<DanKhanEntry>{

    public ReportDanKhanAdapter(@NonNull Context context, ArrayList<DanKhanEntry>danKhanEntryArrayList) {
        super(context, R.layout.report_dan_khan, danKhanEntryArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.table_dan_khan, parent, false);
        }

        TextView date = itemView.findViewById(R.id.table_date);
        //TextView time = itemView.findViewById(R.id.table_time);
        TextView pashudan = itemView.findViewById(R.id.table_pashudan);
        TextView makaibhardo = itemView.findViewById(R.id.table_makaiBhardo);
        TextView papdi = itemView.findViewById(R.id.table_papdi);
        TextView pashuaharOtherType = itemView.findViewById(R.id.pashuahar_other_type);
        TextView pashuaharOtheramt = itemView.findViewById(R.id.pashuahar_other_amt);

        DanKhanEntry danKhanEntry = getItem(position);


        date.setText(danKhanEntry.getDate());
        //time.setText(danKhanEntry.getTime());
        pashudan.setText(danKhanEntry.getPashudan_Dairy());

        makaibhardo.setText(danKhanEntry.getMakai_Own());

        papdi.setText(danKhanEntry.getPapdi_External());

        pashuaharOtherType.setText(danKhanEntry.getPashuahar_Details());

        pashuaharOtheramt.setText(danKhanEntry.getPashushar_Amount());

        return itemView;
    }
}

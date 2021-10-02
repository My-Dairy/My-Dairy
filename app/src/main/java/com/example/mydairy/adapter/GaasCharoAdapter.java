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
import com.example.mydairy.Details.GassCharo;
import com.example.mydairy.R;

import java.util.ArrayList;
import java.util.List;
public class GaasCharoAdapter extends  ArrayAdapter<GassCharo>{
    private TextView checkbox,sukhocharoQuantity,sukhocharoAmount,tractorCharge,seeds,fertilizers,labourCharge,lilocharoSeeds,lilocharofertilizers,date,time;
    private Activity mContext;
    List<GassCharo>gaasCharoList;

    public GaasCharoAdapter(@NonNull Activity mContext , ArrayList<GassCharo>gassCharoArrayList) {
        super(mContext, R.layout.report_gass_charo,gassCharoArrayList);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_table_gaascharo_bill, parent, false);
        }

        TextView date = itemView.findViewById(R.id.table_date);
        //TextView time = itemView.findViewById(R.id.table_time);
        TextView checkbox=itemView.findViewById((R.id.sukoCharo_type));
        TextView sukocharoQuantity = itemView.findViewById(R.id.sukoCharo_Quantity);
        TextView sukocharoAmount = itemView.findViewById(R.id.sukoCharo_Amount);
        TextView tractorCharge = itemView.findViewById(R.id.tractor_charge);
        TextView seeds = itemView.findViewById(R.id.seed_charge);
        TextView fertilizers = itemView.findViewById(R.id.fertilizer_charge);
        TextView labourCharge = itemView.findViewById(R.id.labour_charge);
//        TextView liloCharoSeeds = itemView.findViewById(R.id.liloCharo_seed);
//        TextView lilocharofertilizers = itemView.findViewById(R.id.liloCharo_fertilizer);

        GassCharo gassCharo = getItem(position);

        date.setText(gassCharo.getDate());
        //time.setText(gassCharo.getTime());
        checkbox.setText(gassCharo.getCheckbox());
        sukocharoQuantity.setText(gassCharo.getSukhocharoQuantity());
        sukocharoAmount.setText(gassCharo.getSukhocharoAmount());
        tractorCharge.setText(gassCharo.getTractorCharge());
        seeds.setText(gassCharo.getSeeds());
        fertilizers.setText(gassCharo.getFertilizers());
        labourCharge.setText(gassCharo.getLabourCharge());
//        liloCharoSeeds.setText(gassCharo.getLilocharoSeeds());
//        lilocharofertilizers.setText(gassCharo.getLilocharo_fertilizers());




//        TextView fat = itemView.findViewById(R.id.table_fat);
//        TextView qty = itemView.findViewById(R.id.table_qty);
//        TextView amt = itemView.findViewById(R.id.table_amt);
//
//
//        GassCharo gaascharo = getItem(position);
//
//
//        date.setText(gaascharo.getDate());
//        time.setText(gaascharo.getTime());
//        fat.setText(gaascharo);
//        qty.setText(gaascharo);
//        amt.setText(gaascharo);
//
        return itemView;
    }
}

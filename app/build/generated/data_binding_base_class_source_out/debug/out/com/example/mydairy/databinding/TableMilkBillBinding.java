// Generated by view binder compiler. Do not edit!
package com.example.mydairy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.mydairy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TableMilkBillBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final LinearLayout dateBox;

  @NonNull
  public final TextView tableAmt;

  @NonNull
  public final TextView tableDate;

  @NonNull
  public final TextView tableFat;

  @NonNull
  public final TextView tableQty;

  @NonNull
  public final TextView tableTime;

  private TableMilkBillBinding(@NonNull CardView rootView, @NonNull LinearLayout dateBox,
      @NonNull TextView tableAmt, @NonNull TextView tableDate, @NonNull TextView tableFat,
      @NonNull TextView tableQty, @NonNull TextView tableTime) {
    this.rootView = rootView;
    this.dateBox = dateBox;
    this.tableAmt = tableAmt;
    this.tableDate = tableDate;
    this.tableFat = tableFat;
    this.tableQty = tableQty;
    this.tableTime = tableTime;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static TableMilkBillBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TableMilkBillBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.table_milk_bill, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TableMilkBillBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.date_box;
      LinearLayout dateBox = rootView.findViewById(id);
      if (dateBox == null) {
        break missingId;
      }

      id = R.id.table_amt;
      TextView tableAmt = rootView.findViewById(id);
      if (tableAmt == null) {
        break missingId;
      }

      id = R.id.table_date;
      TextView tableDate = rootView.findViewById(id);
      if (tableDate == null) {
        break missingId;
      }

      id = R.id.table_fat;
      TextView tableFat = rootView.findViewById(id);
      if (tableFat == null) {
        break missingId;
      }

      id = R.id.table_qty;
      TextView tableQty = rootView.findViewById(id);
      if (tableQty == null) {
        break missingId;
      }

      id = R.id.table_time;
      TextView tableTime = rootView.findViewById(id);
      if (tableTime == null) {
        break missingId;
      }

      return new TableMilkBillBinding((CardView) rootView, dateBox, tableAmt, tableDate, tableFat,
          tableQty, tableTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
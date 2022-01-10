// Generated by view binder compiler. Do not edit!
package com.example.mydairy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.mydairy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TableDavaDaruBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final LinearLayout dateBox;

  @NonNull
  public final TextView tableDate;

  @NonNull
  public final TextView tableDose;

  @NonNull
  public final TextView tableMedicalVisit;

  @NonNull
  public final TextView tableMedicine;

  private TableDavaDaruBinding(@NonNull FrameLayout rootView, @NonNull LinearLayout dateBox,
      @NonNull TextView tableDate, @NonNull TextView tableDose, @NonNull TextView tableMedicalVisit,
      @NonNull TextView tableMedicine) {
    this.rootView = rootView;
    this.dateBox = dateBox;
    this.tableDate = tableDate;
    this.tableDose = tableDose;
    this.tableMedicalVisit = tableMedicalVisit;
    this.tableMedicine = tableMedicine;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static TableDavaDaruBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TableDavaDaruBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.table_dava_daru, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TableDavaDaruBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.date_box;
      LinearLayout dateBox = rootView.findViewById(id);
      if (dateBox == null) {
        break missingId;
      }

      id = R.id.table_date;
      TextView tableDate = rootView.findViewById(id);
      if (tableDate == null) {
        break missingId;
      }

      id = R.id.table_dose;
      TextView tableDose = rootView.findViewById(id);
      if (tableDose == null) {
        break missingId;
      }

      id = R.id.table_medical_visit;
      TextView tableMedicalVisit = rootView.findViewById(id);
      if (tableMedicalVisit == null) {
        break missingId;
      }

      id = R.id.table_medicine;
      TextView tableMedicine = rootView.findViewById(id);
      if (tableMedicine == null) {
        break missingId;
      }

      return new TableDavaDaruBinding((FrameLayout) rootView, dateBox, tableDate, tableDose,
          tableMedicalVisit, tableMedicine);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

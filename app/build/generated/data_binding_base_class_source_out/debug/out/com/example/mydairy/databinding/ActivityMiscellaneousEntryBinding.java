// Generated by view binder compiler. Do not edit!
package com.example.mydairy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import com.example.mydairy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMiscellaneousEntryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText electricityBillEdit;

  @NonNull
  public final TextView electricityBillTxt;

  @NonNull
  public final EditText gasBillEdit;

  @NonNull
  public final TextView gasBillTxt;

  @NonNull
  public final EditText maintenanceDetails;

  @NonNull
  public final EditText maintenanceEdit;

  @NonNull
  public final TextView maintenanceTxt;

  @NonNull
  public final EditText othersDetails;

  @NonNull
  public final EditText othersEdit;

  @NonNull
  public final TextView othersTxt;

  @NonNull
  public final Toolbar toolbar;

  private ActivityMiscellaneousEntryBinding(@NonNull RelativeLayout rootView,
      @NonNull EditText electricityBillEdit, @NonNull TextView electricityBillTxt,
      @NonNull EditText gasBillEdit, @NonNull TextView gasBillTxt,
      @NonNull EditText maintenanceDetails, @NonNull EditText maintenanceEdit,
      @NonNull TextView maintenanceTxt, @NonNull EditText othersDetails,
      @NonNull EditText othersEdit, @NonNull TextView othersTxt, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.electricityBillEdit = electricityBillEdit;
    this.electricityBillTxt = electricityBillTxt;
    this.gasBillEdit = gasBillEdit;
    this.gasBillTxt = gasBillTxt;
    this.maintenanceDetails = maintenanceDetails;
    this.maintenanceEdit = maintenanceEdit;
    this.maintenanceTxt = maintenanceTxt;
    this.othersDetails = othersDetails;
    this.othersEdit = othersEdit;
    this.othersTxt = othersTxt;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMiscellaneousEntryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMiscellaneousEntryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_miscellaneous_entry, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMiscellaneousEntryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.electricity_bill_edit;
      EditText electricityBillEdit = rootView.findViewById(id);
      if (electricityBillEdit == null) {
        break missingId;
      }

      id = R.id.electricity_bill_txt;
      TextView electricityBillTxt = rootView.findViewById(id);
      if (electricityBillTxt == null) {
        break missingId;
      }

      id = R.id.gas_bill_edit;
      EditText gasBillEdit = rootView.findViewById(id);
      if (gasBillEdit == null) {
        break missingId;
      }

      id = R.id.gas_bill_txt;
      TextView gasBillTxt = rootView.findViewById(id);
      if (gasBillTxt == null) {
        break missingId;
      }

      id = R.id.maintenance_details;
      EditText maintenanceDetails = rootView.findViewById(id);
      if (maintenanceDetails == null) {
        break missingId;
      }

      id = R.id.maintenance_edit;
      EditText maintenanceEdit = rootView.findViewById(id);
      if (maintenanceEdit == null) {
        break missingId;
      }

      id = R.id.maintenance_txt;
      TextView maintenanceTxt = rootView.findViewById(id);
      if (maintenanceTxt == null) {
        break missingId;
      }

      id = R.id.others_details;
      EditText othersDetails = rootView.findViewById(id);
      if (othersDetails == null) {
        break missingId;
      }

      id = R.id.others_edit;
      EditText othersEdit = rootView.findViewById(id);
      if (othersEdit == null) {
        break missingId;
      }

      id = R.id.others_txt;
      TextView othersTxt = rootView.findViewById(id);
      if (othersTxt == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityMiscellaneousEntryBinding((RelativeLayout) rootView, electricityBillEdit,
          electricityBillTxt, gasBillEdit, gasBillTxt, maintenanceDetails, maintenanceEdit,
          maintenanceTxt, othersDetails, othersEdit, othersTxt, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
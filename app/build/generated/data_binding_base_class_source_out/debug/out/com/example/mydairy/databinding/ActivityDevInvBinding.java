// Generated by view binder compiler. Do not edit!
package com.example.mydairy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.mydairy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDevInvBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText cowPurchaseEdit;

  @NonNull
  public final TextView cowPurchaseTxt;

  @NonNull
  public final EditText cowSellingEdit;

  @NonNull
  public final TextView cowSellingTxt;

  @NonNull
  public final EditText expansionDetails;

  @NonNull
  public final EditText expansionEdit;

  @NonNull
  public final TextView expansionTxt;

  @NonNull
  public final Button save;

  @NonNull
  public final Toolbar toolbar;

  private ActivityDevInvBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText cowPurchaseEdit, @NonNull TextView cowPurchaseTxt,
      @NonNull EditText cowSellingEdit, @NonNull TextView cowSellingTxt,
      @NonNull EditText expansionDetails, @NonNull EditText expansionEdit,
      @NonNull TextView expansionTxt, @NonNull Button save, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.cowPurchaseEdit = cowPurchaseEdit;
    this.cowPurchaseTxt = cowPurchaseTxt;
    this.cowSellingEdit = cowSellingEdit;
    this.cowSellingTxt = cowSellingTxt;
    this.expansionDetails = expansionDetails;
    this.expansionEdit = expansionEdit;
    this.expansionTxt = expansionTxt;
    this.save = save;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDevInvBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDevInvBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dev_inv, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDevInvBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cow_purchase_edit;
      EditText cowPurchaseEdit = rootView.findViewById(id);
      if (cowPurchaseEdit == null) {
        break missingId;
      }

      id = R.id.cow_purchase_txt;
      TextView cowPurchaseTxt = rootView.findViewById(id);
      if (cowPurchaseTxt == null) {
        break missingId;
      }

      id = R.id.cow_selling_edit;
      EditText cowSellingEdit = rootView.findViewById(id);
      if (cowSellingEdit == null) {
        break missingId;
      }

      id = R.id.cow_selling_txt;
      TextView cowSellingTxt = rootView.findViewById(id);
      if (cowSellingTxt == null) {
        break missingId;
      }

      id = R.id.expansion_details;
      EditText expansionDetails = rootView.findViewById(id);
      if (expansionDetails == null) {
        break missingId;
      }

      id = R.id.expansion_edit;
      EditText expansionEdit = rootView.findViewById(id);
      if (expansionEdit == null) {
        break missingId;
      }

      id = R.id.expansion_txt;
      TextView expansionTxt = rootView.findViewById(id);
      if (expansionTxt == null) {
        break missingId;
      }

      id = R.id.save;
      Button save = rootView.findViewById(id);
      if (save == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityDevInvBinding((ConstraintLayout) rootView, cowPurchaseEdit, cowPurchaseTxt,
          cowSellingEdit, cowSellingTxt, expansionDetails, expansionEdit, expansionTxt, save,
          toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
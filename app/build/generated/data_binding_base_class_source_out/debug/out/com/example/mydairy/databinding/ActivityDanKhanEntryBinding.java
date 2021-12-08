// Generated by view binder compiler. Do not edit!
package com.example.mydairy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.mydairy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDanKhanEntryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText makaiExtEdit;

  @NonNull
  public final TextView makaiExtTxt;

  @NonNull
  public final EditText makaiOwnEdit;

  @NonNull
  public final TextView makaiOwnTxt;

  @NonNull
  public final EditText papdiExtrnlEdit;

  @NonNull
  public final TextView papdiExtrnlTxt;

  @NonNull
  public final EditText pashuaharOthrEdit;

  @NonNull
  public final EditText pashuaharOthrSpcfy;

  @NonNull
  public final TextView pashuaharOthrTxt;

  @NonNull
  public final EditText pashudanDairyEdit;

  @NonNull
  public final TextView pashudanDairyTxt;

  @NonNull
  public final EditText pashudanExtrnlEdit;

  @NonNull
  public final TextView pashudanExtrnlTxt;

  @NonNull
  public final FloatingActionButton saveBtn;

  @NonNull
  public final Toolbar toolbar;

  private ActivityDanKhanEntryBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText makaiExtEdit, @NonNull TextView makaiExtTxt, @NonNull EditText makaiOwnEdit,
      @NonNull TextView makaiOwnTxt, @NonNull EditText papdiExtrnlEdit,
      @NonNull TextView papdiExtrnlTxt, @NonNull EditText pashuaharOthrEdit,
      @NonNull EditText pashuaharOthrSpcfy, @NonNull TextView pashuaharOthrTxt,
      @NonNull EditText pashudanDairyEdit, @NonNull TextView pashudanDairyTxt,
      @NonNull EditText pashudanExtrnlEdit, @NonNull TextView pashudanExtrnlTxt,
      @NonNull FloatingActionButton saveBtn, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.makaiExtEdit = makaiExtEdit;
    this.makaiExtTxt = makaiExtTxt;
    this.makaiOwnEdit = makaiOwnEdit;
    this.makaiOwnTxt = makaiOwnTxt;
    this.papdiExtrnlEdit = papdiExtrnlEdit;
    this.papdiExtrnlTxt = papdiExtrnlTxt;
    this.pashuaharOthrEdit = pashuaharOthrEdit;
    this.pashuaharOthrSpcfy = pashuaharOthrSpcfy;
    this.pashuaharOthrTxt = pashuaharOthrTxt;
    this.pashudanDairyEdit = pashudanDairyEdit;
    this.pashudanDairyTxt = pashudanDairyTxt;
    this.pashudanExtrnlEdit = pashudanExtrnlEdit;
    this.pashudanExtrnlTxt = pashudanExtrnlTxt;
    this.saveBtn = saveBtn;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDanKhanEntryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDanKhanEntryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dan_khan_entry, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDanKhanEntryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.makai_ext_edit;
      EditText makaiExtEdit = rootView.findViewById(id);
      if (makaiExtEdit == null) {
        break missingId;
      }

      id = R.id.makai_ext_txt;
      TextView makaiExtTxt = rootView.findViewById(id);
      if (makaiExtTxt == null) {
        break missingId;
      }

      id = R.id.makai_own_edit;
      EditText makaiOwnEdit = rootView.findViewById(id);
      if (makaiOwnEdit == null) {
        break missingId;
      }

      id = R.id.makai_own_txt;
      TextView makaiOwnTxt = rootView.findViewById(id);
      if (makaiOwnTxt == null) {
        break missingId;
      }

      id = R.id.papdi_extrnl_edit;
      EditText papdiExtrnlEdit = rootView.findViewById(id);
      if (papdiExtrnlEdit == null) {
        break missingId;
      }

      id = R.id.papdi_extrnl_txt;
      TextView papdiExtrnlTxt = rootView.findViewById(id);
      if (papdiExtrnlTxt == null) {
        break missingId;
      }

      id = R.id.pashuahar_othr_edit;
      EditText pashuaharOthrEdit = rootView.findViewById(id);
      if (pashuaharOthrEdit == null) {
        break missingId;
      }

      id = R.id.pashuahar_othr_spcfy;
      EditText pashuaharOthrSpcfy = rootView.findViewById(id);
      if (pashuaharOthrSpcfy == null) {
        break missingId;
      }

      id = R.id.pashuahar_othr_txt;
      TextView pashuaharOthrTxt = rootView.findViewById(id);
      if (pashuaharOthrTxt == null) {
        break missingId;
      }

      id = R.id.pashudan_dairy_edit;
      EditText pashudanDairyEdit = rootView.findViewById(id);
      if (pashudanDairyEdit == null) {
        break missingId;
      }

      id = R.id.pashudan_dairy_txt;
      TextView pashudanDairyTxt = rootView.findViewById(id);
      if (pashudanDairyTxt == null) {
        break missingId;
      }

      id = R.id.pashudan_extrnl_edit;
      EditText pashudanExtrnlEdit = rootView.findViewById(id);
      if (pashudanExtrnlEdit == null) {
        break missingId;
      }

      id = R.id.pashudan_extrnl_txt;
      TextView pashudanExtrnlTxt = rootView.findViewById(id);
      if (pashudanExtrnlTxt == null) {
        break missingId;
      }

      id = R.id.save_btn;
      FloatingActionButton saveBtn = rootView.findViewById(id);
      if (saveBtn == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityDanKhanEntryBinding((ConstraintLayout) rootView, makaiExtEdit, makaiExtTxt,
          makaiOwnEdit, makaiOwnTxt, papdiExtrnlEdit, papdiExtrnlTxt, pashuaharOthrEdit,
          pashuaharOthrSpcfy, pashuaharOthrTxt, pashudanDairyEdit, pashudanDairyTxt,
          pashudanExtrnlEdit, pashudanExtrnlTxt, saveBtn, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

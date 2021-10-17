// Generated by view binder compiler. Do not edit!
package com.example.mydairy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.mydairy.R;
import com.github.mikephil.charting.charts.LineChart;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGraphAmtBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LineChart specificGraph;

  @NonNull
  public final LineChart specificGraph1;

  private ActivityGraphAmtBinding(@NonNull LinearLayout rootView, @NonNull LineChart specificGraph,
      @NonNull LineChart specificGraph1) {
    this.rootView = rootView;
    this.specificGraph = specificGraph;
    this.specificGraph1 = specificGraph1;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGraphAmtBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGraphAmtBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_graph_amt, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGraphAmtBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.specific_graph;
      LineChart specificGraph = rootView.findViewById(id);
      if (specificGraph == null) {
        break missingId;
      }

      id = R.id.specific_graph1;
      LineChart specificGraph1 = rootView.findViewById(id);
      if (specificGraph1 == null) {
        break missingId;
      }

      return new ActivityGraphAmtBinding((LinearLayout) rootView, specificGraph, specificGraph1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
// Generated by data binding compiler. Do not edit!
package com.example.mydairy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.mydairy.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentDashboardBinding extends ViewDataBinding {
  @NonNull
  public final TextView textHome;

  protected FragmentDashboardBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView textHome) {
    super(_bindingComponent, _root, _localFieldCount);
    this.textHome = textHome;
  }

  @NonNull
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentDashboardBinding>inflateInternal(inflater, R.layout.fragment_dashboard, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentDashboardBinding>inflateInternal(inflater, R.layout.fragment_dashboard, null, false, component);
  }

  public static FragmentDashboardBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentDashboardBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentDashboardBinding)bind(component, view, R.layout.fragment_dashboard);
  }
}

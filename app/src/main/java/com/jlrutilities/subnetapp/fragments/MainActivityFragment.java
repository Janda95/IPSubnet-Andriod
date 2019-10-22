package com.jlrutilities.subnetapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jlrutilities.subnetapp.R;
import com.jlrutilities.subnetapp.activities.MainActivity;

public class MainActivityFragment extends Fragment {

  private FragmentListener fragmentListener;

  public MainActivityFragment(){
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (!(context instanceof FragmentListener)) throw new AssertionError();
    fragmentListener = (FragmentListener) context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    return rootView;
  }

  public interface FragmentListener {

  }



}


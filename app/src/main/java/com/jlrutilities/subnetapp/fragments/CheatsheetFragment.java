package com.jlrutilities.subnetapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlrutilities.subnetapp.R;

public class CheatsheetFragment extends Fragment {

  public CheatsheetFragment(){
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_cheatsheet, container, false);
    return rootView;
  }
}

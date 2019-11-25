package com.jlrutilities.subnetapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlrutilities.subnetapp.R;

public class ListViewFragment extends Fragment {

  private static final String TAG = "ListViewFragment";

  public ListViewFragment(){ }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_splitter, container, false);
    return rootView;
  }
}

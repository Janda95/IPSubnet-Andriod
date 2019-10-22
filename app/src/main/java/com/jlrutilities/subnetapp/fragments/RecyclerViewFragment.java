package com.jlrutilities.subnetapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlrutilities.subnetapp.R;

public class RecyclerViewFragment extends Fragment {

  private static final String TAG = "RecyclerViewFragment";
  private static final int DATASET_COUNT = 60;
  private String[] mDataSet;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDataset();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_splitter, container, false);
    return rootView;
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  private void initDataset() {
    mDataSet = new String[DATASET_COUNT];
    for (int i = 0; i < DATASET_COUNT; i++){
      mDataSet[i] = "This is element #" + i;
    }
  }
}

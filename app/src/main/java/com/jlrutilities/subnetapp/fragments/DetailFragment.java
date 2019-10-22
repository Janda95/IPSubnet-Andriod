package com.jlrutilities.subnetapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jlrutilities.subnetapp.R;
import com.jlrutilities.subnetapp.models.Node;
import com.jlrutilities.subnetapp.models.SubnetCalculator;


public class DetailFragment extends Fragment {

  private static final String NODE_KEY = "node_key";
  SubnetCalculator subnetCalc;

  TextView usableRangeTv;
  TextView addressRangeTv;
  TextView hostsTv;
  TextView ipTv;
  TextView netmaskTv;
  TextView broadcastTv;

  public DetailFragment() {
  }

  public static DetailFragment newInstance(Node node){

    Bundle args = new Bundle();
    args.putParcelable("NODE_KEY", node);

    DetailFragment fragment = new DetailFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
    return rootView;
  }
}

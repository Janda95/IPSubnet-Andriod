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

  public static final String NODE_KEY = "node_key";

  private TextView usableRangeTv;
  private TextView addressRangeTv;
  private TextView hostsTv;
  private TextView ipTv;
  private TextView netmaskTv;
  private TextView broadcastTv;

  public DetailFragment() {
  }

  public static DetailFragment newInstance(Node node){

    Bundle args = new Bundle();
    args.putParcelable(NODE_KEY, node);

    DetailFragment fragment = new DetailFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
    Node node = getArguments().getParcelable(NODE_KEY);

    broadcastTv = rootView.findViewById( R.id.broadcastTv);
    netmaskTv = rootView.findViewById( R.id.netmaskTv );
    ipTv = rootView.findViewById( R.id.ipTv );
    hostsTv = rootView.findViewById( R.id.hostsTv );
    addressRangeTv = rootView.findViewById( R.id.addressRangeTv);
    usableRangeTv = rootView.findViewById( R.id.usableRangeTv);

    String broadcast = node.getBroadcastIp();
    String ipNetmask = node.getNetmask();
    String ipStr =  node.getIpAddress() + "/" + node.getCidr();
    String numHostsStr = "" + node.getNumberOfHosts();
    String ipRange = node.getFullIpRange();
    String usableRange = node.getUsableIpRange();

    broadcastTv.setText( broadcast );
    netmaskTv.setText( ipNetmask );
    ipTv.setText( ipStr );
    hostsTv.setText( numHostsStr );
    addressRangeTv.setText( ipRange );
    usableRangeTv.setText( usableRange );

    return rootView;
  }
}

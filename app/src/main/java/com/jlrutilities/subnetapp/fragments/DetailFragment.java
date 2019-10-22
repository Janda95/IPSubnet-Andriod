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


/*
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
    subnetCalc = new SubnetCalculator();

    //
    broadcastTv = findViewById(R.id.broadcastTv);
    netmaskTv = findViewById( R.id.netmaskTv );
    ipTv = findViewById( R.id.ipTv );
    hostsTv = findViewById( R.id.hostsTv );
    addressRangeTv = findViewById( R.id.addressRangeTv);
    usableRangeTv = findViewById( R.id.usableRangeTv);
    //

    Node node = getArguments().getParcelable("NODE_KEY");
    int cidr = node.getCidr();
    String address = node.getIpAddress();
    String binaryIp = node.getIpBinary();
    int hosts = node.getNumberOfHosts();


    String ipNetmask = subnetCalc.subnetMask( cidr );
    int numHosts = subnetCalc.numberOfHosts( cidr );
    String ipRange = subnetCalc.rangeOfAddresses(binaryIp, cidr);
    String usableRange = subnetCalc.usableIpAddresses(binaryIp, cidr);
    String broadcast = subnetCalc.broadcastAddress(binaryIp, cidr);

    String ipStr = address + "/" + cidr;
    String numHostsStr = "" + numHosts;

    broadcastTv.setText( broadcast );
    netmaskTv.setText( ipNetmask );
    ipTv.setText( ipStr );
    hostsTv.setText( numHostsStr );
    addressRangeTv.setText( ipRange );
    usableRangeTv.setText( usableRange );

    return super.onCreateView(inflater, container, savedInstanceState);
  }
}
*/
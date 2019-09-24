package com.jlrutilities.subnetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jlrutilities.subnetapp.R;
import com.jlrutilities.subnetapp.models.SubnetCalculator;

public class DescriptionActivity extends AppCompatActivity {

  SubnetCalculator subnetCalc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_description);

    subnetCalc = new SubnetCalculator();

    Intent intent = getIntent();
    TextView broadcastTv = findViewById( R.id.broadcastTv);
    TextView netmaskTv = findViewById( R.id.netmaskTv );
    TextView ipTv = findViewById( R.id.ipTv );
    TextView hostsTv = findViewById( R.id.hostsTv );
    TextView addressRangeTv = findViewById( R.id.addressRangeTv);
    TextView usableRangeTv = findViewById( R.id.usableRangeTv);

    int cidr = intent.getIntExtra( SplitterActivity.CIDR_MESSAGE, -1 );
    String address = intent.getStringExtra( SplitterActivity.ADDRESS_MESSAGE );
    String binaryIp = intent.getStringExtra( SplitterActivity.BINARY_IP_MESSAGE);

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
  }
}

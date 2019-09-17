package com.example.subnetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.subnetapp.R;
import com.example.subnetapp.models.SubnetCalculator;

public class DescriptionActivity extends AppCompatActivity {

  SubnetCalculator subnetCalc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_description);

    subnetCalc = new SubnetCalculator();

    Intent intent = getIntent();
    TextView textView3 = findViewById( R.id.broadcastTv);
    TextView textView4 = findViewById( R.id.netmaskTv );
    TextView textView5 = findViewById( R.id.ipTv );
    TextView textView6 = findViewById( R.id.hostsTv );
    TextView textView7 = findViewById( R.id.addressRangeTv);
    TextView textView8 = findViewById( R.id.usableRangeTv);

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

    textView3.setText( broadcast );
    textView4.setText( ipNetmask );
    textView5.setText( ipStr );
    textView6.setText( numHostsStr );
    textView7.setText( ipRange );
    textView8.setText( usableRange );
  }
}

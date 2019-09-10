package com.example.subnetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.subnetapp.R;
import com.example.subnetapp.models.SubNetCalculator;

public class DescriptionActivity extends AppCompatActivity {

  SubNetCalculator subNetCalc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_description);

    subNetCalc = new SubNetCalculator();

    Intent intent = getIntent();
    TextView textView = findViewById( R.id.cidrTv );
    TextView textView3 = findViewById( R.id.binaryTv );
    TextView textView4 = findViewById( R.id.netmaskTv );
    TextView textView5 = findViewById( R.id.ipTv );
    TextView textView6 = findViewById( R.id.hostsTv );

    int cidr = intent.getIntExtra( SplitterActivity.CIDR_MESSAGE, -1 );
    String address = intent.getStringExtra( SplitterActivity.ADDRESS_MESSAGE );
    String binaryIp = intent.getStringExtra( SplitterActivity.BINARY_IP_MESSAGE) ;

    String subnetMask = subNetCalc.subnetMask( cidr );
    int numHosts = subNetCalc.numberOfHosts( cidr );

    String cidrString = "Cidr: /" + cidr;
    String netmaskStr = "Netmask: " + subnetMask;
    String ipStr = "Ip address: " + address;
    String numHostsStr = "Number of hosts: " + numHosts;

    textView.setText( cidrString );
    textView3.setText( binaryIp );
    textView4.setText( netmaskStr );
    textView5.setText( ipStr );
    textView6.setText( numHostsStr );
  }
}

package com.example.subnetapp.activities;

import android.app.ActionBar;
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
    TextView textView3 = findViewById( R.id.broadcastTv);
    TextView textView4 = findViewById( R.id.netmaskTv );
    TextView textView5 = findViewById( R.id.ipTv );
    TextView textView6 = findViewById( R.id.hostsTv );
    TextView textView7 = findViewById( R.id.addressRangeTv);
    TextView textView8 = findViewById( R.id.usableRangeTv);

    int cidr = intent.getIntExtra( SplitterActivity.CIDR_MESSAGE, -1 );
    String address = intent.getStringExtra( SplitterActivity.ADDRESS_MESSAGE );
    String binaryIp = intent.getStringExtra( SplitterActivity.BINARY_IP_MESSAGE);

    String ipMask = subNetCalc.subnetMask( cidr );
    int numHosts = subNetCalc.numberOfHosts( cidr );
    String ipRange = subNetCalc.rangeOfAddresses(binaryIp, cidr);
    String usableRange = subNetCalc.usableIpAddresses(binaryIp, cidr);
    String broadcast = subNetCalc.broadcastAddress(binaryIp, cidr);

    String broadcastString = "Broadcast: " + broadcast;
    String netmaskStr = ipMask;
    String ipStr = address + "/" + cidr;
    String numHostsStr = "" + numHosts;
    String range = ipRange;
    String usable = usableRange;

    textView3.setText( broadcastString );
    textView4.setText( netmaskStr );
    textView5.setText( ipStr );
    textView6.setText( numHostsStr );
    textView7.setText( range );
    textView8.setText( usable );
  }
}

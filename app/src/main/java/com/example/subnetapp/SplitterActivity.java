package com.example.subnetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplitterActivity extends Activity {

  SubNetCalculator SubnetCalc;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splitter);

    Intent intent = getIntent();
    String ipString = intent.getStringExtra(MainActivity.IP_MESSAGE);
    String cidrString = intent.getStringExtra(MainActivity.CIDR_NETMASK_MESSAGE);
    SubnetCalc = new SubNetCalculator(ipString, cidrString);
  }
}

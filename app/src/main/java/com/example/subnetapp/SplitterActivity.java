package com.example.subnetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SplitterActivity extends AppCompatActivity {

  SubNetCalculator SubnetCalc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splitter);

    Intent intent = getIntent();
    String ipString = intent.getStringExtra(MainActivity.IP_MESSAGE);
    String cidrString = intent.getStringExtra(MainActivity.CIDR_NETMASK_MESSAGE);

    SubnetCalc = new SubNetCalculator(ipString, cidrString);

    TextView textView2 = findViewById(R.id.display_message);
    textView2.setText(Integer.toString(SubnetCalc.getBinaryNumber()));
    TextView textView3 = findViewById(R.id.display_binary_message);
    String binaryNum = String.format("%32s", Integer.toBinaryString(SubnetCalc.getBinaryNumber())).replace(' ', '0');
    textView3.setText(binaryNum);
  }


}

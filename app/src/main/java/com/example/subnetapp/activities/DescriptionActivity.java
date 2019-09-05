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
    TextView textView = findViewById(R.id.cidrTextView);
    TextView textView3 = findViewById(R.id.textView3);
    TextView textView4 = findViewById(R.id.textView4);

    int cidr = intent.getIntExtra(SplitterActivity.CIDR_MESSAGE, -1);
    String address = intent.getStringExtra(SplitterActivity.ADDRESS_MESSAGE);
    String binaryIp = intent.getStringExtra(SplitterActivity.BINARY_IP_MESSAGE);

    textView.setText(address);
    textView3.setText(binaryIp);
    textView4.setText(String.valueOf(cidr));
  }
}

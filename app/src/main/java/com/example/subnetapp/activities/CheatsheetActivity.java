package com.example.subnetapp.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.subnetapp.R;
import com.example.subnetapp.adapters.CheatsheetArrayAdapter;
import com.example.subnetapp.models.SubnetCalculator;

public class CheatsheetActivity extends AppCompatActivity {

  private ListView list;
  String[] bitsArr;
  String[] netmaskArr;
  String[] hostsArr;
  SubnetCalculator subCalc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheatsheet);
    list = findViewById(R.id.cheatsheet_list);
    subCalc = new SubnetCalculator();

    setupValues();
    setupList();

  }

  private void setupValues(){
    bitsArr = new String[25];
    netmaskArr = new String[25];
    hostsArr = new String[25];
    for(int i = 8; i <= 32; i++){
      bitsArr[i-8] = "/" + i;
      netmaskArr[i-8] = subCalc.subnetMask(i);
      hostsArr[i-8] = String.valueOf(subCalc.numberOfHosts(i));
    }
  }

  private void setupList() {
    ArrayAdapter aa = new CheatsheetArrayAdapter(this, bitsArr, netmaskArr, hostsArr);
    list.setAdapter(aa);
  }
}

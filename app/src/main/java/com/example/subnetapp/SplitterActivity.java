package com.example.subnetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SplitterActivity extends AppCompatActivity {

  private static final int EDITOR_REQUEST_CODE = 1001;

  protected static final String EXAMPLE_CONTENT = "";

  private SubNetCalculator SubnetCalc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splitter);

    Intent intent = getIntent();
    String ipString = intent.getStringExtra(MainActivity.IP_STRING_MESSAGE);
    int ipInt = intent.getIntExtra(MainActivity.IP_INT_MESSAGE, 0);
    String cidrString = intent.getStringExtra(MainActivity.CIDR_NETMASK_MESSAGE);

    SubnetCalc = new SubNetCalculator(ipString, cidrString, ipInt);

    ListView list = findViewById(R.id.android_list);

    final String[] localArray = {"1","2","3","4","5"};

    ArrayAdapter aa = new IpArrayAdapter(this, localArray);
    list.setAdapter(aa);

    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //prepare item information to be passed to Description activity
        String example = localArray[position];
        Intent intent = new Intent(SplitterActivity.this, DescriptionActivity.class);
        intent.putExtra(EXAMPLE_CONTENT, example);
        startActivityForResult(intent, EDITOR_REQUEST_CODE);
      }
    });
  }

  //use if decided to use Description Activity to split and merge IP addresses
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    /*if (requestCode == EDITOR_REQUEST_CODE && resultCode == RESULT_OK) {
      //update ip class?
    }*/
  }
}

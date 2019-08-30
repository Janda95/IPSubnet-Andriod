package com.example.subnetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.subnetapp.adapters.IpArrayAdapter;
import com.example.subnetapp.R;
import com.example.subnetapp.models.BinaryTree;
import com.example.subnetapp.models.SubNetCalculator;

public class SplitterActivity extends AppCompatActivity {

  private static final int EDITOR_REQUEST_CODE = 1001;

  protected static final String EXAMPLE_CONTENT = "";
  SubNetCalculator subNetCalc;
  BinaryTree tree;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splitter);

    subNetCalc = new SubNetCalculator();

    Intent intent = getIntent();
    String ipFormatted = intent.getStringExtra(MainActivity.IP_STRING_MESSAGE);
    String cidrString = intent.getStringExtra(MainActivity.CIDR_NETMASK_MESSAGE);
    int cidr = Integer.parseInt(cidrString);
    String ipBinary = subNetCalc.ipFormatToBinary(ipFormatted);
    String cutBinary = subNetCalc.trimCidrIp(ipBinary, cidr);

    tree = new BinaryTree();
    tree.setRoot(cidr, cutBinary, ipFormatted);

    //example list implementation
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
}

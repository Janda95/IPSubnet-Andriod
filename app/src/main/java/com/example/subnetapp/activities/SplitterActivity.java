package com.example.subnetapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.subnetapp.R;
import com.example.subnetapp.adapters.IpArrayAdapter;
import com.example.subnetapp.models.BinaryTree;
import com.example.subnetapp.models.Node;
import com.example.subnetapp.models.SubNetCalculator;

public class SplitterActivity extends AppCompatActivity {

  SubNetCalculator subNetCalc;
  BinaryTree tree;

  private Node[] nodes;
  private String[] nodeIps;
  private int[] nodeLocations = null;
  private int[] cidrArr;
  private int pos = -1;


  AlertDialog.Builder builder;
  AlertDialog alert;
  private ListView list;

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

    //tree list implementation
    list = findViewById(R.id.android_list);

    //refreshListAll for all nodes XOR refreshListBottom for bottom layer nodes
    //refreshListAll();
    refreshListBottom();

    builder = new AlertDialog.Builder(this);

    builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

    //Setting message manually and performing action on button click
    builder.setMessage("What would you like to do?")
        .setCancelable(false)
        .setPositiveButton("Split", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            //finish();
            split();
            dialog.cancel();
          }
        })
        .setNeutralButton("Merge", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            //  Action for 'NO' Button
            merge();
            dialog.cancel();
          }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            //  Action for 'NO' Button
            dialog.cancel();
          }
        });

    //Creating dialog box
    alert = builder.create();

    //On click
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        alert.show();
      }
    });
  }

  //finds parent node and tells parent remove
  private void merge() {
    Node node;

    if(nodeLocations == null) {
      node = nodes[pos];
    } else {
      int refPosition = nodeLocations[pos];
      node = nodes[refPosition];
    }

    if (node == tree.getRoot()) {
      Toast.makeText(getApplicationContext(),"Cannot merge root",
          Toast.LENGTH_SHORT).show();
      return;
    }

    Node parent = tree.findParent(node);

    if (parent == null) {
      Toast.makeText(getApplicationContext(),"Unable to merge",
          Toast.LENGTH_SHORT).show();
      return;
    } else {

      tree.merge(parent);

      if(nodeLocations == null) {
        refreshListAll();
      } else {
        refreshListBottom();
      }
      Toast.makeText(getApplicationContext(),"Merged",
          Toast.LENGTH_SHORT).show();
    }
  }

  private void split() {
    Node node;

    if(nodeLocations == null) {
      node = nodes[pos];
    } else {
      int refPosition = nodeLocations[pos];
      node = nodes[refPosition];
    }

    if (node.getLeft() == null && node.getRight() == null && node.cidr != 32) {
      String splitIp = subNetCalc.ipSplit(node.ipBinary, node.cidr);
      String formatIp = subNetCalc.ipBinaryToFormat(splitIp);
      node.setLeft(node.cidr+1, node.ipBinary, node.ipAddress);
      node.setRight(node.cidr+1, splitIp, formatIp);

      if(nodeLocations == null) {
        refreshListAll();
      } else {
        refreshListBottom();
      }
      Toast.makeText(getApplicationContext(),"Merged",
          Toast.LENGTH_SHORT).show();
    }
  }

  private void refreshListAll(){
    nodes = new Node[tree.size()];
    nodeIps = new String[nodes.length];
    cidrArr = new int[nodes.length];

    for(int i = 0; i < nodes.length; i++){
      nodeIps[i] = nodes[i].getIpAddress();
      cidrArr[i] = nodes[i].getCidr();
    }

    ArrayAdapter aa = new IpArrayAdapter(this, nodeIps, cidrArr);
    list.setAdapter(aa);
  }

  private void refreshListBottom(){
    //Bottom Layer Nodes
    nodes = new Node[tree.size()];
    nodeIps = new String[tree.sizeBottomLayer()];
    nodeLocations = new int[nodeIps.length];
    cidrArr = new int[nodeIps.length];


    for(int i = 0; i < nodes.length; i++){
      nodes[i] = tree.nthPreordernode(i+1);
    }

    //Bottom Layer Nodes
    int counter = 0;
    for(int i = 0; i < nodes.length; i++) {
      Node node = nodes[i];
      if(node.getLeft() == null && node.getRight() == null){
        nodeIps[counter] = node.getIpAddress();
        cidrArr[counter] = node.getCidr();
        nodeLocations[counter] = i;
        counter++;
      }
    }

    ArrayAdapter aa = new IpArrayAdapter(this, nodeIps, cidrArr);
    list.setAdapter(aa);
  }
}

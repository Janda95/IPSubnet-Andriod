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
import com.example.subnetapp.models.Node;
import com.example.subnetapp.models.SubNetCalculator;

public class SplitterActivity extends AppCompatActivity {

  private static final int EDITOR_REQUEST_CODE = 1001;

  protected static final String EXAMPLE_CONTENT = "";
  SubNetCalculator subNetCalc;
  BinaryTree tree;

  private Node[] nodes;
  private String[] nodeIps;
  private int[] nodeLocations = null;

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

    //On click
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Node node;

        if(nodeLocations == null) {
          node = nodes[position];
        } else {
          int refPosition = nodeLocations[position];
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
        }
      }
    });

    //On long click
    list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //PLACEHOLDER location for description list transition

        //prepare item information to be passed to Description activity
        //String example = nodeIps[position];
        //Intent intent = new Intent(SplitterActivity.this, DescriptionActivity.class);
        //intent.putExtra(EXAMPLE_CONTENT, example);
        //startActivityForResult(intent, EDITOR_REQUEST_CODE);

        //default
        return false;
      }
    });
  }

  private void refreshListAll(){
    nodes = new Node[tree.size()];
    nodeIps = new String[nodes.length];

    for(int i = 0; i < nodes.length; i++){
      nodeIps[i] = nodes[i].getIpAddress();
    }

    ArrayAdapter aa = new IpArrayAdapter(this, nodeIps);
    list.setAdapter(aa);
  }

  private void refreshListBottom(){
    //Bottom Layer Nodes
    nodes = new Node[tree.size()];
    nodeIps = new String[tree.sizeBottomLayer()];
    nodeLocations = new int[nodeIps.length];

    for(int i = 0; i < nodes.length; i++){
      nodes[i] = tree.nthPreordernode(i+1);
    }

    //Bottom Layer Nodes
    int counter = 0;
    for(int i = 0; i < nodes.length; i++) {
      Node node = tree.nthPreordernode(i+1 );
      if(node.getLeft() == null && node.getRight() == null){
        nodeIps[counter] = node.getIpAddress();
        nodeLocations[counter] = i;
        counter++;
      }
    }

    ArrayAdapter aa = new IpArrayAdapter(this, nodeIps);
    list.setAdapter(aa);
  }
}

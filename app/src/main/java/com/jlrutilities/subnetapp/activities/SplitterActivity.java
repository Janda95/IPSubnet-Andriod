package com.jlrutilities.subnetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jlrutilities.subnetapp.R;
import com.jlrutilities.subnetapp.adapters.IpArrayAdapter;
import com.jlrutilities.subnetapp.fragments.DetailFragment;
import com.jlrutilities.subnetapp.fragments.HelpDialogFragment;
import com.jlrutilities.subnetapp.models.BinaryTree;
import com.jlrutilities.subnetapp.models.Node;
import com.jlrutilities.subnetapp.models.SubnetCalculator;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

public class SplitterActivity extends AppCompatActivity {

  private static final String MY_TREE = "my_tree";
  SubnetCalculator subnetCalc;
  BinaryTree tree;

  private Node[] nodes;
  private String[] nodeIps;
  private int[] nodeLocations = null;
  private int[] cidrArr;
  private int[] numOfHostsArr;
  private int pos = -1;

  private ListView list;
  SwipeActionAdapter mAdapter;
  private boolean mTwoPane;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splitter);
    subnetCalc = new SubnetCalculator();

    if(savedInstanceState == null){

      Intent intent = getIntent();
      String ipFormatted = intent.getStringExtra(MainActivity.IP_STRING_MESSAGE);
      String cidrString = intent.getStringExtra(MainActivity.CIDR_NETMASK_MESSAGE);

      int cidr = Integer.parseInt(cidrString);
      String ipBinary = subnetCalc.ipFormatToBinary(ipFormatted);

      int numOfHosts = subnetCalc.numberOfHosts(cidr);
      String netmask = subnetCalc.subnetMask( cidr );
      String range = subnetCalc.rangeOfAddresses(ipBinary, cidr);
      String usableRange = subnetCalc.usableIpAddresses(ipBinary, cidr);

      String broadcast = subnetCalc.broadcastAddress(ipBinary, cidr);

      tree = new BinaryTree();
      tree.setRoot(cidr, ipBinary, ipFormatted, numOfHosts, broadcast, range, usableRange , netmask);

      //tree list implementation
    } else {
      tree = savedInstanceState.getParcelable(MY_TREE);
    }

    //Check if MasterDetailView Available
    ViewGroup fragmentContainer = findViewById(R.id.fragment_detail_container);
    if (fragmentContainer != null) {
      mTwoPane = true;
    }

    list = findViewById(R.id.android_list);
    refreshList();

    //On click
    list.setOnItemClickListener((parent, view, position, id) -> {
      if(mTwoPane){
        //create and populate fragment container
        FragmentManager fragmentManager = getSupportFragmentManager();

        int refPosition = nodeLocations[position];
        Node node = nodes[refPosition];

        DetailFragment fragment = DetailFragment.newInstance(node);
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_detail_container, fragment)
            .commit();
      } else {
        // Transition to Description Activity
        Intent intent1 = new Intent(getApplicationContext(), DescriptionActivity.class);

        int refPosition = nodeLocations[position];
        Node node = nodes[refPosition];

        intent1.putExtra("node_key", node);

        startActivity(intent1);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.splitter_menu, menu);
    return true;
  }

 @Override
 protected void onSaveInstanceState(Bundle savedInstanceState){
    super.onSaveInstanceState(savedInstanceState);
    savedInstanceState.putParcelable(MY_TREE, tree);
 }

  //finds parent node and tells parent remove
  private void merge(){
    int refPosition = nodeLocations[pos];
    Node node = nodes[refPosition];

    if ( node == tree.getRoot() ){
      Toast.makeText(getApplicationContext(),"Cannot merge root",
          Toast.LENGTH_SHORT).show();
      return;
    }

    Node parent = tree.findParent(node);

    if (parent == null){
      Toast.makeText(getApplicationContext(),"Unable to merge",
          Toast.LENGTH_SHORT).show();
      return;
    } else {
      tree.merge(parent);
      refreshList();
      Toast.makeText(getApplicationContext(),"Merged",
          Toast.LENGTH_SHORT).show();
    }
  }

  private void split(){
    int refPosition = nodeLocations[pos];
    Node node = nodes[refPosition];
    int cidr = node.getCidr();
    String binaryIp = node.getIpBinary();

    if (node.getLeft() == null && node.getRight() == null && cidr != 32) {
      // Split current node and set child nodes
      String splitIp = subnetCalc.ipSplit(node.getIpBinary(), cidr);
      String formatIp = subnetCalc.ipBinaryToFormat(splitIp);
      int numOfHosts = subnetCalc.numberOfHosts(cidr+1);
      String newNetmask = subnetCalc.subnetMask( cidr+1 );

      // Left Split
      String leftRange = subnetCalc.rangeOfAddresses(binaryIp, cidr+1);
      String leftUsableRange = subnetCalc.usableIpAddresses(binaryIp, cidr+1);
      String leftBroadcast = subnetCalc.broadcastAddress(binaryIp, cidr+1);

      //Right Split
      String rightRange = subnetCalc.rangeOfAddresses(splitIp, cidr+1);
      String rightUsableRange = subnetCalc.usableIpAddresses(splitIp, cidr+1);
      String rightBroadcast = subnetCalc.broadcastAddress(splitIp, cidr+1);

      node.setLeft(cidr+1, node.getIpBinary(), node.getIpAddress(), numOfHosts, leftBroadcast, leftRange, leftUsableRange, newNetmask);
      node.setRight(cidr+1, splitIp, formatIp, numOfHosts, rightBroadcast, rightRange, rightUsableRange, newNetmask );

      refreshList();
      Toast.makeText(getApplicationContext(),"Split",
          Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(getApplicationContext(),"Cannot Split /32",
          Toast.LENGTH_SHORT).show();
    }
  }

  private void refreshList(){
    //Bottom Layer Nodes
    nodes = new Node[tree.size()];
    nodeIps = new String[tree.sizeBottomLayer()];
    nodeLocations = new int[nodeIps.length];
    cidrArr = new int[nodeIps.length];
    numOfHostsArr = new int[nodeIps.length];

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
        numOfHostsArr[counter] = node.getNumberOfHosts();
        nodeLocations[counter] = i;
        counter++;
      }
    }

    ArrayAdapter aa = new IpArrayAdapter(this, nodeIps, cidrArr, numOfHostsArr);
    mAdapter = new SwipeActionAdapter(aa);
    mAdapter.setListView(list);
    list.setAdapter(mAdapter);

    setSwipeFunctionality();
  }

  private void setSwipeFunctionality(){
    // Set backgrounds for the swipe directions
    mAdapter.addBackground(SwipeDirection.DIRECTION_NORMAL_LEFT,R.layout.row_bg_left)
        .addBackground(SwipeDirection.DIRECTION_NORMAL_RIGHT,R.layout.row_bg_right);

    mAdapter.setSwipeActionListener(new SwipeActionAdapter.SwipeActionListener(){
      @Override
      public boolean hasActions(int position, SwipeDirection direction){
        if(direction.isLeft()) return true;
        if(direction.isRight()) return true;
        return false;
      }

      @Override
      public boolean shouldDismiss(int position, SwipeDirection direction) {
        switch (direction) {
          case DIRECTION_NORMAL_LEFT:
          case DIRECTION_FAR_LEFT:
          case DIRECTION_NORMAL_RIGHT:
          case DIRECTION_FAR_RIGHT:
            return true;
          case DIRECTION_NEUTRAL:
            break;
        }
        return false;
      }

      @Override
      public void onSwipe(int[] positionList, SwipeDirection[] directionList) {
        for(int i=0;i<positionList.length;i++) {
          SwipeDirection direction = directionList[i];
          int position = positionList[i];

          switch (direction) {
            case DIRECTION_NORMAL_LEFT:
            case DIRECTION_FAR_LEFT:
              pos = position;
              merge();
              break;
            case DIRECTION_NORMAL_RIGHT:
            case DIRECTION_FAR_RIGHT:
              pos = position;
              split();
              break;
          }
        }
      }
    });
  }

  public void displayHelpDialog(MenuItem item) {
    HelpDialogFragment dialogFragment = new HelpDialogFragment();
    //dialogFragment.setCancelable(false);
    dialogFragment.show(getSupportFragmentManager(), "DIALOG_FRAGMENT");
  }
}

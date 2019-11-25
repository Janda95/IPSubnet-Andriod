package com.jlrutilities.subnetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jlrutilities.subnetapp.R;
import com.jlrutilities.subnetapp.adapters.CheatsheetArrayAdapter;
import com.jlrutilities.subnetapp.fragments.IpAdjustmentDialogFragment;
import com.jlrutilities.subnetapp.models.SubnetCalculator;

public class MainActivity extends AppCompatActivity implements IpAdjustmentDialogFragment.IpAdjustmentDialogListener {

  SubnetCalculator subnetCalc;
  EditText inputTextView;
  Spinner spinner;
  AlertDialog.Builder builder;
  private int defaultNetmask;

  private ListView list;
  String[] bitsArr;
  String[] netmaskArr;
  String[] hostsArr;

  protected static final String IP_STRING_MESSAGE = "com.example.IPSTRING.Message";
  protected static final String CIDR_NETMASK_MESSAGE = "com.example.NETMASK.Message";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }

  private void init() {
    subnetCalc = new SubnetCalculator();

    //setup list fragment
    list = findViewById(R.id.cheatsheet_list);
    setupTableValues();
    setupList();

    //Setup form input fragment
    //Init TextView
    inputTextView = findViewById(R.id.ipEntryTv);

    //Init Spinner
    spinner = findViewById(R.id.subnet_spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.split_locations, R.layout.spinner_item);
    adapter.setDropDownViewResource(R.layout.spinner_item);
    spinner.setAdapter(adapter);
    defaultNetmask = 16; // default is "/24"
    spinner.setSelection(defaultNetmask);

    //Dialog Builder
    builder = new AlertDialog.Builder(MainActivity.this);
  }

  //Transition to Cheatsheet View
  public void viewCheat(Menu item) {
    Intent intent = new Intent( this, CheatsheetActivity.class);
    startActivity(intent);
  }

  //Transition to Splitter View
  public void subnetTransition(View view) {
    //Get Text From View
    String message = inputTextView.getText().toString();
    String[] ipArray = message.split("\\.");

    //InputValidation
    boolean check = isValid(ipArray);
    if (check == false) {
      return;
    }

    //Get Netmask From Spinner
    Spinner spinner = findViewById(R.id.subnet_spinner);
    String spinnerItem = spinner.getSelectedItem().toString();

    int cidr = Integer.parseInt(spinnerItem);
    String ipBinary = subnetCalc.ipFormatToBinary(message);
    String cutBinary = subnetCalc.trimCidrIp(ipBinary, cidr);
    String ipFormatted = subnetCalc.ipBinaryToFormat(cutBinary);

    //extra periods fix
    message = subnetCalc.ipBinaryToFormat(ipBinary);

    if (ipFormatted.equals(message)){
      Intent intent = new Intent( this, SplitterActivity.class);
      intent.putExtra(IP_STRING_MESSAGE, message);
      intent.putExtra(CIDR_NETMASK_MESSAGE, spinnerItem);
      startActivity(intent);
    } else {
      //Ask user if adjustment is ok
      IpAdjustmentDialogFragment dialogFragment = IpAdjustmentDialogFragment.newInstance(message, ipFormatted, spinnerItem);
      dialogFragment.show(getSupportFragmentManager(), "DIALOG_FRAGMENT");
    }
  }

  //Reset input
  public void clearInput(View view){
    inputTextView.setText("");
    spinner.setSelection(defaultNetmask);
  }

  //Validate Input
  private boolean isValid(String[] array) {
    //Number of Tokens
    if (array.length != 4) {
      Toast.makeText(getApplicationContext(),"IP Invalid", Toast.LENGTH_SHORT).show();
      return false;
    }

    //String array to Integer array for checks
    Integer[] intArray = new Integer[4];
    for (int i = 0; i < array.length; i++) {
      try {
        intArray[i] = Integer.parseInt(array[i]);
      } catch (NumberFormatException nfe) {
        Toast.makeText(getApplicationContext(),"IP Invalid", Toast.LENGTH_SHORT).show();
        return false;
      }
    }

    //First Section of IP cannot be zero
    if (intArray[0] == 0) {
      Toast.makeText(getApplicationContext(),"IP Invalid", Toast.LENGTH_SHORT).show();
      return false;
    }

    //Testing bounds for valid input
    for (int i = 0; i < intArray.length; i++) {
      if ( intArray[i] >= 256 ) {
        Toast.makeText(getApplicationContext(),"IP Invalid", Toast.LENGTH_SHORT).show();
        return false;
      } else if (intArray[i] < 0) {
        Toast.makeText(getApplicationContext(),"IP Invalid", Toast.LENGTH_SHORT).show();
        return false;
      }
    }
    return true;
  }

  @Override
  public void onDialogPositiveClick(DialogFragment dialog, String ipAddress, String cidr) {
    //Intent
    Intent intent = new Intent( this, SplitterActivity.class);
    intent.putExtra(IP_STRING_MESSAGE, ipAddress);
    intent.putExtra(CIDR_NETMASK_MESSAGE, cidr);
    startActivity(intent);
    dialog.dismiss();
  }

  @Override
  public void onDialogNegativeClick(DialogFragment dialog) {
    dialog.dismiss();
  }

  private void setupTableValues(){
    bitsArr = new String[25];
    netmaskArr = new String[25];
    hostsArr = new String[25];

    //Generate CIDR 8-32 Values
    for(int i = 8; i <= 32; i++){
      bitsArr[i-8] = "/" + i;
      netmaskArr[i-8] = subnetCalc.subnetMask(i);
      hostsArr[i-8] = String.valueOf(subnetCalc.numberOfHosts(i));
    }
  }

  private void setupList() {
    ArrayAdapter aa = new CheatsheetArrayAdapter(this, bitsArr, netmaskArr, hostsArr);
    list.setAdapter(aa);
  }
}

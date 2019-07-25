package com.example.subnetapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

  Spinner spinner;
  SubNetCalculator newCalc;
  AlertDialog.Builder builder;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initVariables();
  }

  private void initVariables(){
    //Init Spinner
    spinner = findViewById(R.id.subnet_spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.split_locations, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    //Init SubnetCalculator
    newCalc = new SubNetCalculator();

    //Dialog Builder
    builder = new AlertDialog.Builder(this);
  }

  public void viewCheat(View view){
    Intent intent = new Intent( this, CheatsheetActivity.class);
    startActivity(intent);
  }

  public void printOutput(View view){
    EditText editText = findViewById(R.id.editText);
    String message = editText.getText().toString();
    String[] ipArray = message.split("\\.");
    System.out.print(message);

    //Testing Code for Dialog Box

    //Uncomment the below code to Set the message and title from the strings.xml file
    builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

    //Setting message manually and performing action on button click
    builder.setMessage("Do you want to close this application ?")
        .setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            finish();
            Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                Toast.LENGTH_SHORT).show();
          }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            //  Action for 'NO' Button
            dialog.cancel();
            Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                Toast.LENGTH_SHORT).show();
          }
        });
    //Creating dialog box
    AlertDialog alert = builder.create();
    //Setting the title manually
    alert.setTitle("AlertDialogExample");
    alert.show();

  }

  private void ipInputValidation(String[] array){


  }

}

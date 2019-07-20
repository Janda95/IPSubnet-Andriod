package com.example.subnetapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CheatsheetActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheatsheet);
    setupTextView();
  }

  private void setupTextView(){
    TextView textView = findViewById(R.id.contentTextView);

    String hostNumberInfo =
      "/#    Hosts\n" +
      "/8:   16,777,214\n" +
      "/9:   8,388,606\n" +
      "/10:  4,194,302\n" +
      "/11:  2,097,150\n" +
      "/12:  1,048,574\n" +
      "/13:  524,286\n" +
      "/14:  262,142\n" +
      "/15:  131,070\n" +
      "/16:  65,534\n" +
      "/17:  32,766\n" +
      "/18:  16,382\n" +
      "/19:  8,190\n" +
      "/20:  4,094\n" +
      "/21:  2,046\n" +
      "/22:  1,022\n" +
      "/23:  510\n" +
      "/24:  254\n" +
      "/25:  126\n" +
      "/26:  62\n" +
      "/27:  30\n" +
      "/28:  14\n" +
      "/29:  6\n" +
      "/30:  2\n" +
      "/31:  2\n" +
      "/32:  1" ;

    textView.setText(hostNumberInfo);
  }


}

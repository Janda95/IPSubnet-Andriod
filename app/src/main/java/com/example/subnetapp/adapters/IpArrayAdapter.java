package com.example.subnetapp.adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subnetapp.R;
import com.example.subnetapp.models.Node;

public class IpArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;
  private final int[] cidrArr;

  /*public IpArrayAdapter(Context context, String[] values) {
    super(context, -1, values);
    this.context = context;
    this.values = values;
  }*/


  public IpArrayAdapter(Context context, String[] values, int[] cidr) {
    super(context, -1, values);
    this.context = context;
    this.values = values;
    this.cidrArr = cidr;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.ip_list_item, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.tvIpItem);
    ImageView imageView = (ImageView) rowView.findViewById(R.id.imageListIcon);
    TextView textView2 = (TextView) rowView.findViewById(R.id.cidrIpItem);
    textView.setText(values[position]);
    textView2.setText("/" + Integer.toString(cidrArr[position]));
    //Each row at position in row
    //String s = values[position];
    //example if s = thing1 then imageView = otherPicResource

    return rowView;
  }


}

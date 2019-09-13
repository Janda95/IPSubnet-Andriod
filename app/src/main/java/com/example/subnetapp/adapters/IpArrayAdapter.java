package com.example.subnetapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subnetapp.R;

public class IpArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;
  private final int[] cidrArr;
  private final int[] hosts;

  public IpArrayAdapter(Context context, String[] values, int[] cidr, int[] hosts) {
    super(context, -1, values);
    this.context = context;
    this.values = values;
    this.cidrArr = cidr;
    this.hosts = hosts;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.ip_list_item, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.tvIpItem);
    //ImageView imageView = (ImageView) rowView.findViewById(R.id.imageListIcon);
    TextView textView2 = (TextView) rowView.findViewById(R.id.cidrIpItem);
    TextView textView3 = rowView.findViewById(R.id.hostsTv);

    String cidrStr = Integer.toString(cidrArr[position]);
    String hostsStr = "Usable Hosts: " + hosts[position];

    textView.setText(values[position]);
    textView2.setText("/" + cidrStr);
    textView3.setText(hostsStr);

    return rowView;
  }


}

package com.jlrutilities.subnetapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jlrutilities.subnetapp.R;

public class CheatsheetArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] bitsArr;
  private final String[] netmaskArr;
  private final String[] hostsArr;

  public CheatsheetArrayAdapter(Context context, String[] values, String[] netmaskArr, String[] hostsArr) {
    super(context, -1, values);
    this.context = context;
    this.bitsArr = values;
    this.netmaskArr = netmaskArr;
    this.hostsArr = hostsArr;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.cheatsheet_list_item, parent, false);

    TextView bitsTv = rowView.findViewById(R.id.bitsItemTv);
    TextView netmaskTv = rowView.findViewById(R.id.netmaskItemTv);
    TextView hostsTv = rowView.findViewById(R.id.hostsItemTv);

    bitsTv.setText(bitsArr[position]);
    netmaskTv.setText(netmaskArr[position]);
    hostsTv.setText(hostsArr[position]);

    return rowView;
  }
}

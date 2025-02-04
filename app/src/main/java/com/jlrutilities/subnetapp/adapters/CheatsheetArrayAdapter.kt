package com.jlrutilities.subnetapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jlrutilities.subnetapp.R


//** Allows subnet context to be passed and reference view information. */
class CheatsheetArrayAdapter(
        context: Context,
        values: Array<String>,
        netmaskArr: Array<String>,
        hostsArr: Array<String>
): ArrayAdapter<String>(context, -1, values){

  private val context: Context
  private val bitsArr: Array<String>
  private val netmaskArr: Array<String>
  private val hostsArr: Array<String>

  init {
    this.context = context;
    this.bitsArr = values;
    this.netmaskArr = netmaskArr;
    this.hostsArr = hostsArr;
  }


  //** Sets subnet information in view. */
  override fun getView(
          position: Int,
          convertView: View?,
          parent: ViewGroup
  ): View {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    // TODO: ViewHolder Pattern
    val rowView: View = inflater.inflate(R.layout.cheatsheet_list_item, parent, false);

    val bitsTv: TextView = rowView.findViewById(R.id.bitsItemTv);
    val netmaskTv: TextView = rowView.findViewById(R.id.netmaskItemTv);
    val hostsTv: TextView = rowView.findViewById(R.id.hostsItemTv);

    bitsTv.text = bitsArr[position]
    netmaskTv.text = netmaskArr[position]
    hostsTv.text = hostsArr[position]

    return rowView;
  }
}

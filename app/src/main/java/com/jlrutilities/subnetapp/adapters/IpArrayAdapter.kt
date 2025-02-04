package com.jlrutilities.subnetapp.adapters;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jlrutilities.subnetapp.R


//** Allows Ip information to be passed and reference view information. */
class IpArrayAdapter(
        context: Context,
        values: Array<String>,
        cidr: IntArray,
        hosts: IntArray
): ArrayAdapter<String>(context, -1, values){

  private val context: Context
  private val values: Array<String>
  private val cidrArr: IntArray
  private val hosts: IntArray

  init {
    this.context = context;
    this.values = values;
    this.cidrArr = cidr;
    this.hosts = hosts;
  }


  override fun getView(
          position: Int,
          convertView: View?,
          parent: ViewGroup
  ): View {
    // TODO: check layoutinflator is correct before update to store
    val inflater: LayoutInflater = LayoutInflater.from(context)
    // TODO: ViewHolderPattern
    val rowView: View = inflater.inflate(R.layout.ip_list_item, parent, false)

    val ipTv: TextView = rowView.findViewById(R.id.tvIpItem)
    val cidrTv: TextView = rowView.findViewById(R.id.cidrIpItem)
    val hostsTv: TextView = rowView.findViewById(R.id.hostsTv)

    val cidrStr: String = "/" + cidrArr[position].toString()
    val hostPosition: Int = hosts[position]
    val hostsStr = "Usable Hosts: $hostPosition"

    ipTv.text = values[position]
    cidrTv.text = cidrStr
    hostsTv.text = hostsStr

    return rowView
  }
}

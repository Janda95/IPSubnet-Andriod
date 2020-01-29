package com.jlrutilities.subnetapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Node implements Parcelable {
  // Values
  private String ipAddress;
  private String ipBinary;
  private String broadcastIp;
  private String fullIpRange;
  private String usableIpRange;
  private String netmask;
  private int cidr;
  private int numberOfHosts;

  // Child nodes
  private Node left;
  private Node right;

  //Constructors
  public Node(int cidr, String ipBinary, String ipAddress, int numberOfHosts, String broadcastIp, String fullIpRange, String usableIpRange, String netmask) {
    this.cidr = cidr;
    this.ipBinary = ipBinary;
    this.ipAddress = ipAddress;
    this.numberOfHosts = numberOfHosts;
    this.broadcastIp = broadcastIp;
    this.fullIpRange = fullIpRange;
    this.usableIpRange = usableIpRange;
    this.netmask = netmask;

    left = null;
    right = null;
  }

  //setters
  public void setLeft(int cidr, String ipBinary, String ipAddress, int numberOfHosts, String broadcastIp, String fullIpRange, String usableIpRange, String netmask){
    left = new Node(cidr, ipBinary, ipAddress, numberOfHosts, broadcastIp, fullIpRange, usableIpRange, netmask);
  }

  public void setRight(int cidr, String ipBinary, String ipAddress, int numberOfHosts, String broadcastIp, String fullIpRange, String usableIpRange, String netmask){
    right = new Node(cidr, ipBinary, ipAddress, numberOfHosts, broadcastIp, fullIpRange, usableIpRange, netmask);
  }

  //getters
  public int getCidr(){
    return cidr;
  }

  public String getIpBinary(){
    return ipBinary;
  }

  public String getIpAddress(){
    return ipAddress;
  }

  public int getNumberOfHosts() { return numberOfHosts; }

  public String getBroadcastIp() { return broadcastIp; }

  public String getFullIpRange() { return fullIpRange; }

  public String getUsableIpRange() { return usableIpRange; }

  public String getNetmask() { return netmask; }

  public Node getLeft(){
    return left;
  }

  public Node getRight(){
    return right;
  }

  public void setChildrenNull(){
    left = null;
    right = null;
  }

  protected Node(Parcel in) {
    ipAddress = in.readString();
    ipBinary = in.readString();
    cidr = in.readInt();
    numberOfHosts = in.readInt();
    broadcastIp = in.readString();
    fullIpRange = in.readString();
    usableIpRange = in.readString();
    netmask = in.readString();
    left = (Node) in.readValue(Node.class.getClassLoader());
    right = (Node) in.readValue(Node.class.getClassLoader());
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(ipAddress);
    dest.writeString(ipBinary);
    dest.writeInt(cidr);
    dest.writeInt(numberOfHosts);
    dest.writeString(broadcastIp);
    dest.writeString(fullIpRange);
    dest.writeString(usableIpRange);
    dest.writeString(netmask);
    dest.writeValue(left);
    dest.writeValue(right);
  }

  @SuppressWarnings("unused")
  public static final Parcelable.Creator<Node> CREATOR = new Parcelable.Creator<Node>() {
    @Override
    public Node createFromParcel(Parcel in) {
      return new Node(in);
    }

    @Override
    public Node[] newArray(int size) {
      return new Node[size];
    }
  };
}

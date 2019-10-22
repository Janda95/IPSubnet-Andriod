package com.jlrutilities.subnetapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Node implements Parcelable {
  //values
  public String ipAddress;
  public String ipBinary;
  public int cidr;
  public int numberOfHosts;

  private Node left;
  private Node right;

  //Constructors
  public Node(int cidr, String ipBinary, String ipAddress, int numberOfHosts) {
    this.cidr = cidr;
    this.ipBinary = ipBinary;
    this.ipAddress = ipAddress;
    this.numberOfHosts = numberOfHosts;

    left = null;
    right = null;
  }

  //setters
  public void setLeft(int cidr, String ipBinary, String ipAddress, int numberOfHosts){
    left = new Node(cidr, ipBinary, ipAddress, numberOfHosts);
  }

  public void setRight(int cidr, String ipBinary, String ipAddress, int numberOfHosts){
    right = new Node(cidr, ipBinary, ipAddress, numberOfHosts);
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

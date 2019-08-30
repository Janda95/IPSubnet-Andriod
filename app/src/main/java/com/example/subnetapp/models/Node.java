package com.example.subnetapp.models;

public class Node {
  //values
  public String ipAddress;
  public int cidr;
  public String ipBinary;

  private Node left;
  private Node right;

  //Constructors
  public Node(int cidr, String ipBinary, String ipAddress) {

    this.cidr = cidr;
    this.ipBinary = ipBinary;
    this.ipAddress = ipAddress;

    left = null;
    right = null;
  }

  //setters
  public void setLeft(int cidr, String ipBinary, String ipAddress){
    left = new Node(cidr, ipBinary, ipAddress);
  }

  public void setRight(int cidr, String ipBinary, String ipAddress){
    right = new Node(cidr, ipBinary, ipAddress);
  }

  //getters
  public int getCidr(){
    return cidr;
  }

  public String getIpBinary(){
    return ipBinary;
  }

  public Node getLeft(){
    return left;
  }

  public Node getRight(){
    return right;
  }

}

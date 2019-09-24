package com.jlrutilities.subnetapp.models;

public class Node {
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

}

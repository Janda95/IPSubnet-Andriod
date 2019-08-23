package com.example.subnetapp.models;

public class Node {
  //values
  public int cidr;
  public String ipBinary;
  public String formatedIp;

  int key;

  public Node left;
  public Node right;

  public Node(int item){
    key = item;
    left = null;
    right = null;
  }
}

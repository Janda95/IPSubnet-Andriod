package com.example.subnetapp.models;

public class Node {
  //values
  /*public int cidr;
  public String ipBinary;
  public String formatedIp;*/

  private int key;

  private Node left;
  private Node right;

  public Node(int item){
    key = item;
    left = null;
    right = null;
  }

  public int getKey(){
    return key;
  }

  public Node getLeft(){
    return left;
  }

  public Node getRight(){
    return right;
  }

  public void setLeft(int number){
    left = new Node(number);
  }

  public void setRight(int number){
    right = new Node(number);
  }

}

package com.jlrutilities.subnetapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BinaryTree implements Parcelable {

  private Node root;
  private int flag = 0;

  public BinaryTree(){
    root = null;
  }

  public Node getRoot() {
    return root;
  }

  public void setRoot(int cidr, String ipBinary, String ipAddress, int numberOfHosts, String broadcastIp, String fullIpRange, String usableIpRange, String netmask) {
    root = new Node(cidr, ipBinary, ipAddress, numberOfHosts, broadcastIp, fullIpRange, usableIpRange, netmask);
  }

  //Find Nth node starting with root
  private Node nthPreordernode(Node node, int N) {

    if (node == null) {
      return null;
    }

    if (flag <= N) {
      flag++;

      // returns the n-th node of preorder traversal
      if (flag == N){
        return node;
      }

      // left recursion
      Node returnedNode = nthPreordernode(node.getLeft(), N);

      if (returnedNode == null){
        // right recursion
        returnedNode = nthPreordernode(node.getRight(), N);
      } else {
        return returnedNode;
      }

      if (returnedNode != null) {
        return returnedNode;
      }
    }
    return null;
  }

  private void size(Node node) {
    if (node == null) {
      return;
    }

    flag ++;

    size(node.getLeft());
    size(node.getRight());
  }

  private void sizeBottomLayer(Node node){
    if(node == null){
      return;
    }

    if (node.getLeft() == null && node.getRight() == null){
      flag++;
    }

    sizeBottomLayer(node.getLeft());
    sizeBottomLayer(node.getRight());
  }

  public Node findParent(Node node, Node child){
    if (node == null) {
      return null;
    }

    // returns the n-th node of preorder traversal
    if (node.getLeft() == child || node.getRight() == child){
      return node;
    }

    // left recursion
    Node returnedNode = findParent(node.getLeft(), child);

    if (returnedNode == null){
      // right recursion
      returnedNode = findParent(node.getRight(), child);
    } else {
      return returnedNode;
    }

    if (returnedNode != null) {
      return returnedNode;
    }

    return null;
  }

  public void merge(Node node){
    if (node == null) {
      return;
    }

    Node left = node.getLeft();
    Node right = node.getRight();

    if ( left == null && right == null) {
      return;
    }

    merge(node.getLeft());
    merge(node.getRight());

    node.setChildrenNull();
  }

  //Size of entire tree
  public int size() {
    size(root);
    int value = flag;
    flag = 0;
    return value;
  }

  //Size of lowest layer of each branch
  public int sizeBottomLayer() {
    sizeBottomLayer(root);
    int value = flag;
    flag = 0;
    return value;
  }

  public Node nthPreordernode(int N) {
    Node node = nthPreordernode(root, N);
    flag = 0;
    return node;
  }

  public Node findParent(Node child) {
    Node node = findParent(root, child);
    return node;
  }

  protected BinaryTree(Parcel in) {
    root = (Node) in.readValue(Node.class.getClassLoader());
    flag = in.readInt();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(root);
    dest.writeInt(flag);
  }

  @SuppressWarnings("unused")
  public static final Parcelable.Creator<BinaryTree> CREATOR = new Parcelable.Creator<BinaryTree>() {
    @Override
    public BinaryTree createFromParcel(Parcel in) {
      return new BinaryTree(in);
    }

    @Override
    public BinaryTree[] newArray(int size) {
      return new BinaryTree[size];
    }
  };
}
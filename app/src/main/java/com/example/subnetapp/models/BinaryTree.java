package com.example.subnetapp.models;

public class BinaryTree {

  private Node root;
  private int flag = 0;

  public BinaryTree(){
    root = null;
  }

  public Node getRoot() {
    return root;
  }

  public void setRoot(int cidr, String ipBinary, String ipAddress) {
    root = new Node(cidr, ipBinary, ipAddress);
  }

  public void printPostorder(Node node) {
    if (node == null)
      return;

    // first recur on left subtree
    printPostorder(node.getLeft());

    // then recur on right subtree
    printPostorder(node.getRight());

    // now deal with the node
    System.out.print(node.getCidr() + " ");
  }

  public void printInorder(Node node) {
    if (node == null)
      return;

    /* first recur on left child */
    printInorder(node.getLeft());

    /* then print the data of node */
    System.out.print(node.getCidr() + " ");

    /* now recur on right child */
    printInorder(node.getRight());
  }

  public void printPreorder(Node node) {
    if (node == null)
      return;

    /* first print data of node */
    System.out.print(node.getCidr() + " ");

    /* then recur on left sutree */
    printPreorder(node.getLeft());

    /* now recur on right subtree */
    printPreorder(node.getRight());
  }

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
    if (node == null){
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


  // Wrappers over above recursive functions
  public void printPostorder() { printPostorder(root); }
  public void printInorder() { printInorder(root); }
  public void printPreorder() { printPreorder(root); }

  public int size() {
    size(root);
    int value = flag;
    flag = 0;
    return value;
  }

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

}
package com.example.subnetapp.models;

public class BinaryTree {
  private Node root;
  private static int flag = 0;

  public BinaryTree(){
    root = null;
  }

  public Node getRoot() {
    return root;
  }

  /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
  public void printPostorder(Node node) {
    if (node == null)
      return;

    // first recur on left subtree
    printPostorder(node.getLeft());

    // then recur on right subtree
    printPostorder(node.getRight());

    // now deal with the node
    System.out.print(node.getKey() + " ");
  }

  /* Given a binary tree, print its nodes in inorder*/
  public void printInorder(Node node) {
    if (node == null)
      return;

    /* first recur on left child */
    printInorder(node.getLeft());

    /* then print the data of node */
    System.out.print(node.getKey() + " ");

    /* now recur on right child */
    printInorder(node.getRight());
  }

  /* Given a binary tree, print its nodes in preorder*/
  public void printPreorder(Node node) {
    if (node == null)
      return;

    /* first print data of node */
    System.out.print(node.getKey() + " ");

    /* then recur on left sutree */
    printPreorder(node.getLeft());

    /* now recur on right subtree */
    printPreorder(node.getRight());
  }

  // Wrappers over above recursive functions
  public void printPostorder()  { printPostorder(root); }
  public void printInorder()    { printInorder(root); }
  public void printPreorder()   { printPreorder(root); }

  public void NthPreordernode(int N) {
    NthPreordernode(root, N);
    flag = 0;
  }
  public Node NthPreordernode2(int N) {
    Node node = NthPreordernode2(root, N);
    flag = 0;
    return node;
  }

  public void setRoot(int number) {
    root = new Node(number);
  }


  public void NthPreordernode(Node root, int N) {

    if (root == null) {
      return;
    }

    if (flag <= N) {
      flag++;

      // prints the n-th node of preorder traversal
      if (flag == N){
        System.out.println( root.getKey() );
      }

      // left recursion
      NthPreordernode(root.getLeft(), N);

      // right recursion
      NthPreordernode(root.getRight(), N);
    }
  }


  public Node NthPreordernode2(Node node, int N) {

    if (node == null) {
      System.out.println("NULL");
      return null;
    }

    if (flag <= N) {
      flag++;

      // prints the n-th node of preorder traversal
      if (flag == N){
        System.out.println("This is the inner node value: " + node.getKey());
        return node;
      }

      // left recursion
      Node returnedNode = NthPreordernode2(node.getLeft(), N);

      if (returnedNode == null){
        // right recursion
         returnedNode = NthPreordernode2(node.getRight(), N);
      } else {
        return returnedNode;
      }

      if (returnedNode != null) {
        return returnedNode;
      }
    }
    return null;
  }


}
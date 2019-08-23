package com.example.subnetapp.models;

public class BinaryTree {
  private Node root;

  public BinaryTree(){
    root = null;
  }

  public Node getRoot() {
    return root;
  }

  /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
  public void printPostorder(Node node)
  {
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
  public void printInorder(Node node)
  {
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
  public void printPreorder(Node node)
  {
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
  public void printPostorder()  {     printPostorder(root);  }
  public void printInorder()    {     printInorder(root);   }
  public void printPreorder()   {     printPreorder(root);  }

  public void setRoot(int number) {
    root = new Node(number);
  }
}
package com.example.subnetapp.models;

public class BinaryTree {
  Node root;

  BinaryTree(){
    root = null;
  }
  /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
  void printPostorder(Node node)
  {
    if (node == null)
      return;

    // first recur on left subtree
    printPostorder(node.left);

    // then recur on right subtree
    printPostorder(node.right);

    // now deal with the node
    System.out.print(node.key + " ");
  }

  /* Given a binary tree, print its nodes in inorder*/
  void printInorder(Node node)
  {
    if (node == null)
      return;

    /* first recur on left child */
    printInorder(node.left);

    /* then print the data of node */
    System.out.print(node.key + " ");

    /* now recur on right child */
    printInorder(node.right);
  }

  /* Given a binary tree, print its nodes in preorder*/
  void printPreorder(Node node)
  {
    if (node == null)
      return;

    /* first print data of node */
    System.out.print(node.key + " ");

    /* then recur on left sutree */
    printPreorder(node.left);

    /* now recur on right subtree */
    printPreorder(node.right);
  }
}
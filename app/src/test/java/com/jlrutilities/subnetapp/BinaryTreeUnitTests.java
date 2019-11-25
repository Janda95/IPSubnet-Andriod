package com.jlrutilities.subnetapp;

import com.jlrutilities.subnetapp.models.BinaryTree;
import com.jlrutilities.subnetapp.models.Node;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryTreeUnitTests {
  BinaryTree binaryTree = new BinaryTree();

  public void setupTreeDummyData_3Layers_5Nodes(){
    binaryTree.setRoot(1, "0000", "1.2.3.4", 1,  "", "", "", "");
    Node root = binaryTree.getRoot();
    root.setLeft(2, "0001", "1.2.3.4", 2, "", "", "", "");
    root.setRight(3, "0010", "1.2.3.4", 3,  "", "", "", "");
    root.getLeft().setLeft(4, "0011", "1.2.3.4", 4, "", "", "", "");
    root.getLeft().setRight(5, "0100", "1.2.3.4", 5,  "", "", "", "");
  }

  @Test
  public void binaryTree_NthPreorderNode_Test(){
    setupTreeDummyData_3Layers_5Nodes();
    int location = 3;
    Node node = binaryTree.nthPreordernode(location);
    assertEquals(4, node.getCidr());
  }

  @Test
  public void binaryTree_NthPreorderNode_OutOfBounds_NotFound_Test(){
    setupTreeDummyData_3Layers_5Nodes();
    int location = 6;
    Node node = binaryTree.nthPreordernode(location);
    assertEquals(null, node);
  }

  @Test
  public void binaryTree_Size_Test(){
    setupTreeDummyData_3Layers_5Nodes();
    int size = binaryTree.size();
    assertEquals(5, size);
  }

  @Test
  public void binaryTree_Size_BottomLayer_Test(){
    setupTreeDummyData_3Layers_5Nodes();
    int size = binaryTree.sizeBottomLayer();
    assertEquals(3, size);
  }

  @Test
  public void binaryTree_FindParent_Test(){
    setupTreeDummyData_3Layers_5Nodes();
    Node node = binaryTree.nthPreordernode(3);
    Node parent = binaryTree.findParent(node);
    assertEquals(2, parent.getCidr());
  }

  @Test
  public void binaryTree_FindParent_RootParentNull_Test(){
    setupTreeDummyData_3Layers_5Nodes();
    Node root = binaryTree.getRoot();
    Node parent = binaryTree.findParent(root);
    assertEquals(null, parent);
  }

  @Test
  public void binaryTree_merge_Test(){
    setupTreeDummyData_3Layers_5Nodes();
    Node node = binaryTree.getRoot().getLeft();
    binaryTree.merge(node);
    int newSize = binaryTree.size();
    assertEquals(3, newSize);
  }
}

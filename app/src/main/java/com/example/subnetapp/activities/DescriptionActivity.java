package com.example.subnetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.subnetapp.R;
import com.example.subnetapp.models.BinaryTree;
import com.example.subnetapp.models.Node;

public class DescriptionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_description);

    Intent intent = getIntent();
    TextView textView = findViewById(R.id.editText);
    textView.setText((String) intent.getStringExtra(SplitterActivity.EXAMPLE_CONTENT));

    BinaryTree tree = new BinaryTree();
    tree.setRoot(1);
    tree.getRoot().setLeft(2);
    tree.getRoot().setRight(3);
    tree.getRoot().getLeft().setLeft(4);
    tree.getRoot().getLeft().setRight(5);

    System.out.println("\n");

    /*System.out.println("Preorder traversal of binary tree is ");
    tree.printPreorder();

    System.out.println("\nInorder traversal of binary tree is ");
    tree.printInorder();

    System.out.println("\nPostorder traversal of binary tree is ");
    tree.printPostorder();*/

    Node[] array = new Node[7];

    for(int i =0; i < array.length; i++) {
      Node node = tree.NthPreordernode2(i);
      array[i] = node;
    }

    System.out.println("\n");

    for(int i = 0; i < array.length; i++) {
      if (array[i] == null) {
        System.out.println("Failure to find Node!");
      } else {
        int temp = array[i].getKey();
        System.out.println("This is returned node int: " + temp + "! YAY!");
        System.out.println("\n");
      }
    }

    System.out.println("\n");


  }

}

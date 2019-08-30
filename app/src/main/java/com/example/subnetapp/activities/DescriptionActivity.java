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
    /*tree.setRoot(1);
    tree.getRoot().setLeft(2);
    tree.getRoot().setRight(3);
    tree.getRoot().getLeft().setLeft(4);
    tree.getRoot().getLeft().setRight(5);
    tree.getRoot().getRight().setLeft(6);
    tree.getRoot().getRight().setRight(7);*/

    System.out.println("\n");

    /*
    //print  pre, in, and post order values
    System.out.println("Preorder traversal of binary tree is ");
    tree.printPreorder();

    System.out.println("\nInorder traversal of binary tree is ");
    tree.printInorder();

    System.out.println("\nPostorder traversal of binary tree is ");
    tree.printPostorder();
    */

    /*Node[] array = new Node[8];

    for (int i = 0; i < array.length; i++) {
      Node node = tree.nthPreordernode(i);
      array[i] = node;
    }

    System.out.println("\n");

    //expected order for console output null, 1, 2, 4, 5, 3, null
    for (int i = 0; i < array.length; i++) {
      if (array[i] == null) {
        System.out.println("Null");
      } else {
        int num = array[i].getCidr();
        System.out.println(num);
        System.out.println("\n");
      }
    }
    System.out.println(tree.size());
    System.out.println("\n");*/


  }
}

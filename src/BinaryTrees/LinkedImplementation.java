package BinaryTrees;

import java.util.Scanner;

class BinaryTree{
    public BinaryTree() {
    }

    class Node{
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    // insert elements in binary tree
    public void populate(Scanner sc){
        System.out.print("Enter the root node value: ");
        int value = sc.nextInt();
        root = new Node(value);
        populate(sc,root);
    }

    private void populate(Scanner sc, Node node) {
        System.out.print("Do you want to enter left of " + node.val + " : ");
        boolean left = sc.nextBoolean();
        if(left){
            System.out.print("Enter the value of left of " + node.val + " : ");
            int leftVal = sc.nextInt();
            node.left = new Node(leftVal);
            populate(sc,node.left);
        }

        System.out.print("Do you want to enter right of " + node.val + " : ");
        boolean right = sc.nextBoolean();
        if(right){
            System.out.print("Enter the value of right of " + node.val + " : ");
            int rightVal = sc.nextInt();
            node.right = new Node(rightVal);
            populate(sc,node.right);
        }
    }

    public void display() {
        display(root, " ",false);
    }

//    private void display(Node node, String space) {
//        if(node==null) return;
//        System.out.println(space + node.val);
//        display(node.left,space + "\t");
//        display(node.right,space + "\t");
//    }
    private void display(Node root, String prefix, boolean isLeft) {
        if (root != null) {
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.val);
        display(root.left, prefix + (isLeft ? "│   " : "    "), true);
        display(root.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}

public class LinkedImplementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinaryTree bt = new BinaryTree();
        bt.populate(sc);
        bt.display();
    }
}

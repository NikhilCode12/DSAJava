package BinarySearchTrees;

import java.util.Scanner;

public class BST {
    public BST() {
    }

    private class Node{
        int val;
        Node left;
        Node right;
        int height;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(int val, Node left, Node right, int height) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    private Node root;

    public int height(Node node){
        if(node == null) return -1;
        return node.height;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(int value){
        root = insert(value,root);
    }

    public Node insert(int value,Node node){
        if(node == null){
            node = new Node(value);
            return node;
        }

        if(value < node.val){
            node.left = insert(value, node.left);
        }

        if(value > node.val){
            node.right = insert(value, node.right);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

    public void display() {
        display(root, "",false);
    }
    private void display(Node root, String prefix, boolean isLeft) {
        if (root != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.val + " ~ (" + root.height + ")");
            display(root.left, prefix + (isLeft ? "│   " : "    "), true);
            display(root.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    // to check if tree is balanced or not
    public boolean balanced(){
        return balanced(root);
    }

    private boolean balanced(Node node){
        if(node == null){
            return true;
        }
        return Math.abs(height(node.right) - height(node.left)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null){
            return;
        }

        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null){
            return;
        }

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST bst = new BST();
//        bst.populate(sc);
//        bst.insert(5);
//        bst.insert(10);
//        bst.insert(6);
//        bst.insert(1);
//        bst.insert(7);
//        bst.insert(4);

//        balanced example
//        bst.insert(5);
//        bst.insert(6);
//        bst.insert(1);
//        bst.display();
//        if(bst.balanced()){
//            System.out.println("The tree is balanced");
//        } else System.out.println("Tree is unbalanced!");

//        Traversals example
        int[] values = {10,20,12,15,30};
        for (int i = 0; i < values.length; i++) {
            bst.insert(values[i]);
        }
        bst.display();
        // preorder traversal
        System.out.print("Preorder: ");
        bst.preOrder();
        System.out.println();

        // inorder traversal
        System.out.print("Inorder(should be sorted in case of bst): ");
        bst.inOrder();
        System.out.println();

        // postorder traversal
        System.out.print("Postorder: ");
        bst.postOrder();
        System.out.println();
    }
}

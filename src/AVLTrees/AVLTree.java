package AVLTrees;

import BinarySearchTrees.BST;

// First, since AVL is also a BST at first.It has all the properties of BST.
public class AVLTree {
    public AVLTree() {
    }
    class Node{
        int val;
        Node left;
        Node right;

        int height;

        public Node(int val) {
            this.val = val;
        }

        public int getValue(){
            return val;
        }
    }

    private Node root;

    public int height(){
        return height(root);
    }
    public int height(Node node){
        if(node == null){
            return -1;
        }
        return node.height;
    }
    public void insert(int value){
        root = insert(root,value);
    }

    private Node insert(Node node,int value){
        if(node == null){
            node = new Node(value);
            return node;
        }

        if(value < node.val){
            node.left = insert(node.left,value);
        }

        if(value > node.val){
            node.right = insert(node.right,value);
        }

        node.height = Math.max(height(node.right), height(node.left)) + 1;
        return rotate(node);
    }

    private Node rotate(Node node){

        // 4 test cases for rotation
        // 1,2: left heavy
        if(height(node.left) - height(node.right) > 1){

            // Left-left case
            if(height(node.left.left) - height(node.left.right) > 0){
                //right rotate
                return rightRotate(node);
            }

            // Left-right case
            if(height(node.left.left) - height(node.left.right) < 0){
                //left rotate child, then right rotate the parent
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        // 3,4: right heavy
        if(height(node.left) - height(node.right) < -1){
            // right-right case
            if(height(node.right.left) - height(node.right.right) < 0){
                // left rotate
                return leftRotate(node);
            }

            // right-left case
            if(height(node.right.left) - height(node.right.right) > 0){
                // right rotate then left rotate parent
                node.right = leftRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    private Node leftRotate(Node child) {
        Node parent = child.right;
        Node t = parent.left;

        parent.left = child;
        child.right = t;

        parent.height = Math.max(height(parent.left),height(parent.right) + 1);
        child.height = Math.max(height(child.left),height(child.right) + 1);
        return parent;
    }

    private Node rightRotate(Node node) {
        Node child = node.left;
        Node t = child.right;

        child.right = node;
        node.left = t;

        node.height = Math.max(height(node.left),height(node.right) + 1);
        child.height = Math.max(height(child.left),height(child.right) + 1);
        return child;
    }

    public boolean isEmpty(){
        return root == null;
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

    public boolean balanced(){
        return balanced(root);
    }

    private boolean balanced(Node node){
        if(node == null){
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    public static void main(String[] args) {
        int[] tree = {10,20,30,40,50};
        AVLTree avlTree = new AVLTree();
        BST bst = new BST();

        for(int i=0;i<tree.length;i++){
            avlTree.insert(tree[i]);
            bst.insert(tree[i]);
        }

        System.out.println("---- AVL TREE ----");
        avlTree.display();
        System.out.println("------ BST -------");
        bst.display();
        System.out.println("Height of the tree: " + avlTree.height());
    }
}

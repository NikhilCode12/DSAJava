package SegmentTrees;

import java.util.*;

public class SegmentTree {
    class Node{
        int data;
        int startInterval;
        int endInterval;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node(int startInterval, int endInterval) {
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }

    public SegmentTree() {
    }

    private Node root;
    public void create(int[] values){
        root = create(values,0,values.length-1);
    }

    public Node create(int[] values,int first, int last){
        // if leaf node then return
        if(first == last){
            Node leaf = new Node(first,last);
            leaf.data = values[first];
            return leaf;
        }

        Node node = new Node(first,last);
        int mid = (first + last)/2;
        node.left = create(values,first,mid);
        node.right  = create(values,mid+1,last);

        node.data = node.left.data + node.right.data;
        return node;
    }

    public void display(){
        display(root,"",false);
    }

    private void display(Node root, String prefix, boolean isLeft) {
        if (root != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.data + " ~ [" + root.startInterval + "," + root.endInterval + "]");
            display(root.left, prefix + (isLeft ? "│   " : "    "), true);
            display(root.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public int query(int queryStart, int queryEnd){
        return query(root,queryStart,queryEnd);
    }

    private int query(Node node, int queryStart, int queryEnd) {
        if(queryStart <= node.startInterval && queryEnd >= node.endInterval) return node.data;
        else if(node.startInterval > queryEnd || node.endInterval < queryStart){
            return 0; //in case of sum
        }
        return query(node.left, queryStart, queryEnd) + query(node.right,queryStart,queryEnd);
    }

    public void update(int index,int value){
        root.data =  update(root,index,value);
    }

    private int update(Node node, int index, int value) {
        if(index >= node.startInterval && index <= node.endInterval){
            if(index == node.startInterval && index == node.endInterval){
                node.data = value;
                return node.data;
            }
            else{
                node.data = update(node.left, index, value) + update(node.right, index, value);
                return node.data;
            }
        }
        return node.data;
    }

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree();
        int[] values = {3,8,7,6,-2,-8,4,9};
        st.create(values);
        st.display();
        System.out.println("Sum of values from 2 to 6 are " + st.query(1,6));
        st.update(3,14);
        st.display();
    }
}

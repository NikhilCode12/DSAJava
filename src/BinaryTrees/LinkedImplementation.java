package BinaryTrees;

import java.util.Scanner;

public class LinkedImplementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinaryTree bt = new BinaryTree();
        bt.populate(sc);
        bt.display();
    }
}

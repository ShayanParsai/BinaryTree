package com.company;

public class Main {

    public static void main(String[] args) {
        int[] test = {5, 3, 789, 1235, 2, 1, 3, 4, 23, 43};
        BubbleSort sortAndPrint = new BubbleSort();
        sortAndPrint.bubbleSort(test);
        BinaryTree tree = new BinaryTree();

        tree.insert(10);
        tree.insert(2);
        tree.insert(16);
        tree.insert(1);
        tree.insert(4);
        tree.insert(3);
        tree.insert(7);
        tree.insert(5);
        tree.insert(8);
        tree.insert(9);
        tree.printTreeInOrder();

        System.out.print("\n");

        tree.remove(4);
        tree.printTreeInOrder();
    }
}

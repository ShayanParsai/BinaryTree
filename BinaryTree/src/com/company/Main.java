package com.company;

public class Main {

    public static void main(String[] args) {
        int[] test = {5, 3, 79, 15, 2, 1, 3, 4, 23, 43};
        BubbleSort sortAndPrint = new BubbleSort();
        System.out.println("====== Print Bubble Sorted List ======\n");
        sortAndPrint.bubbleSort(test);
        // ==================== \\

        BinaryTree tree = new BinaryTree();

        tree.insert(10);
        tree.insert(16);
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        tree.insert(3);
        tree.insert(7);
        tree.insert(5);
        tree.insert(8);
        tree.insert(9);
        tree.insert(32);
        tree.insert(-64);
        tree.insert(-42);
        tree.insert(-128);

        System.out.println("\n ====== Print Pre Order ====== \n");
        tree.printPreOrder();
        System.out.println("\n ====== Print In Order ====== \n");
        tree.printInOrder();
        System.out.println("\n ====== Print In Reverse Order ====== \n");
        tree.printInReverseOrder();
        System.out.println("\n ====== Print Post Order ====== \n");
        tree.printPostOrder();
    }
}

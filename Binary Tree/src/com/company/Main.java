package com.company;

public class Main {

    private Node root;

    private static class Node {
        Node left;
        Node right;
        int key;

        Node(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        int[] test = {5, 3, 789, 1235, 2, 1, 3, 4, 23, 43};
        BubbleSort sortAndPrint = new BubbleSort();
        sortAndPrint.bubbleSort(test);
        Main tree = new Main();

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
        
        tree.remove(2);
        tree.remove(16);
        tree.printTreeInOrder();
    }

    private void insert(int key) {
        root = insertRecursive(root, key);
    }

    private Node insertRecursive(Node tree, int key) {
        if (tree == null) {
            tree = new Node(key);
            return tree;
        }

        if (key < tree.key) {
            tree.left = insertRecursive(tree.left, key);
        } else if (key > tree.key) {
            tree.right = insertRecursive(tree.right, key);
        }
        return tree;
    }

    private void remove(int key) {
        removeRecursive(root, null, key);
    }

    private void removeRecursive(Node tree, Node parentTree, int keyToRemove) {
        if (keyToRemove < tree.key) {
            if (tree.left != null) {
                removeRecursive(tree.left, tree, keyToRemove);
            } else {
                parentTree.left = tree;
            }
        } else {
            if (tree.right != null) {
                removeRecursive(tree.right, tree, keyToRemove);
            }
        }
    }

    private void printTreeInOrder() {
        printTreeInOrder(root);
    }

    private void printTreeInOrder(Node tree) {
        if (tree == null) {
            return;
        }
        printTreeInOrder(tree.left);
        System.out.println(tree.key);
        printTreeInOrder(tree.right);
    }

    private void printTreeInReverseOrder() {
        printTreeInReverseOrder(root);
    }

    private void printTreeInReverseOrder(Node tree) {
        if (tree == null) {
            return;
        }
        printTreeInReverseOrder(tree.right);
        System.out.println(tree.key);
        printTreeInReverseOrder(tree.left);
    }
}
package com.company;

import javax.management.openmbean.KeyAlreadyExistsException;

public class BinaryTree {

    private Node root;

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    public void remove(int value) {
        /*
                10
              /   \
             1      16
              \
               2
                \
                 4
                / \
               3   7
                  / \
                 5   8
                      \
                       9
         */
        removeRecursive(root, null, value);
    }

    private void removeRecursive(Node currentTree, Node parentTree, int valueToRemove) {

        if (valueToRemove < currentTree.value && currentTree.hasLeftChild()) {
            removeRecursive(currentTree.left, currentTree, valueToRemove);

        } else if (valueToRemove > currentTree.value && currentTree.hasRightChild()) {
            removeRecursive(currentTree.right, currentTree, valueToRemove);

        } else {
            if (parentTree == null) {
                Node left = currentTree.left;
                root = currentTree.right;
                insertRecursive(root, left);
                return;
            }
            if (parentTree.hasRightChild() && parentTree.right.hasTwoChildren()) {
                Node currentLeft = parentTree.right.left;
                Node currentRight = parentTree.right.right;
                parentTree.right = currentRight;
                insertRecursive(currentRight, currentLeft);
            }

            else if (parentTree.hasRightChild() && parentTree.right.hasRightChild()) {
                Node currentRight = parentTree.right.right;
                parentTree.right = currentRight;
            }

            else if (parentTree.hasRightChild() && parentTree.right.hasLeftChild()) {
                Node currentLeft = parentTree.right.left;
            }
        }
    }

    private Node insertRecursive(Node tree, int value) {
        if (tree == null) {
            tree = new Node(value);
            return tree;
        }
        if (value < tree.value) {
            tree.left = insertRecursive(tree.left, value);
        } else if (value > tree.value) {
            tree.right = insertRecursive(tree.right, value);
        } else {
            throw new KeyAlreadyExistsException(String.format("The value '%s' already exists", value));
        }
        return tree;
    }

    private Node insertRecursive(Node parentNode, Node nodeToInsert) {
        if (parentNode == null) {
            return nodeToInsert;
        }
        if (nodeToInsert.value < parentNode.value) {
            parentNode.left = insertRecursive(parentNode.left, nodeToInsert);
        } else if (nodeToInsert.value > parentNode.value) {
            parentNode.right = insertRecursive(parentNode.right, nodeToInsert);
        } else {
            throw new KeyAlreadyExistsException(String.format("The value '%s' already exists", parentNode.value));
        }
        return parentNode;
    }

    public void printTreeInOrder() {
        printTreeInOrder(root);
    }

    public void printTreeInReverseOrder() {
        printTreeInReverseOrder(root);
    }

    private void printTreeInOrder(Node tree) {
        if (tree == null) {
            return;
        }
        printTreeInOrder(tree.left);
        System.out.println(tree.value);
        printTreeInOrder(tree.right);
    }

    private void printTreeInReverseOrder(Node tree) {
        if (tree == null) {
            return;
        }
        printTreeInReverseOrder(tree.right);
        System.out.println(tree.value);
        printTreeInReverseOrder(tree.left);
    }

    private static class Node {
        Node left;
        Node right;
        int value;

        Node(int value) {
            this.value = value;
        }

        boolean hasLeftChild() {
            return left != null;
        }

        boolean hasRightChild() {
            return right != null;
        }

        boolean hasTwoChildren() {
            return hasLeftChild() && hasRightChild();
        }

        boolean hasOneChild() {
            return hasLeftChild() ^ hasRightChild();
        }

        @Override
        public String toString() {
            return String.format("Node(%s)", value);
        }
    }
}
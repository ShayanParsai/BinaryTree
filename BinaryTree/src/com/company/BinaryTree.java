package com.company;

import javax.management.openmbean.KeyAlreadyExistsException;

public class BinaryTree {

    private Node root;

    public void remove(int value) {
        /*
                    10
                  /   \
                 1     16
                / \      \
              -64  2      32
              /  \  \
           -128 -42  4
                    / \
                   3   7
                      / \
                     5   8
                          \
                           9
         */

        removeRecursive(root, null, value);
    }

    private void removeRecursive(Node currentNode, Node parentNode, int valueToRemove) {

        if (valueToRemove < currentNode.value && currentNode.hasLeftChild()) {
            removeRecursive(currentNode.left, currentNode, valueToRemove);

        } else if (valueToRemove > currentNode.value && currentNode.hasRightChild()) {
            removeRecursive(currentNode.right, currentNode, valueToRemove);

        } else {

            /*
                When a node is removed, it is replaced by its right node.
                If the node of removal lacks a right node, it is instead replaced by its left node.
                If the node of removal has two branches, it will be replaced by the node on its right
                and the node on its left will be inserted into the new branch
             */

            if (currentNode.hasTwoChildren()) {
                Node branchLeft = currentNode.left;
                Node branchRight = currentNode.right;

                if (currentNode == root) {
                    root = insertRecursive(null, branchRight);
                    insertRecursive(root, branchLeft);
                    return;
                }

                // Is current node its parent's right child?
                if (currentNode == parentNode.right) {
                    parentNode.right = insertRecursive(null, branchRight); // Cut off right branch, trim, and reattach
                    insertRecursive(parentNode.right, branchLeft); // Attach left branch to the right branch
                } else {
                    parentNode.left = insertRecursive(null, branchLeft); // Cut off left branch, trim, and reattach
                    insertRecursive(parentNode.left, branchRight); // Attach right branch to the left branch
                }

            } else if (currentNode.hasLeftChild()) {
                Node branchLeft = currentNode.left;

                if (currentNode == root) {
                    root = insertRecursive(null, branchLeft);
                    return;
                }

                if (currentNode == parentNode.right)
                    parentNode.right = insertRecursive(null, branchLeft);
                else
                    parentNode.left = insertRecursive(null, branchLeft);

            } else if (currentNode.hasRightChild()) {
                Node branchRight = currentNode.right;

                if (currentNode == root) {
                    root = insertRecursive(null, branchRight);
                    return;
                }

                if (currentNode == parentNode.right)
                    parentNode.right = insertRecursive(null, branchRight);
                else
                    parentNode.left = insertRecursive(null, branchRight);

            } else {

                if (currentNode == parentNode.right)
                    parentNode.right = null;
                else
                    parentNode.left = null;

            }
        }
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
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
            // This exception should never be thrown
            throw new KeyAlreadyExistsException();
        }
        return parentNode;
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node tree) {
        if (tree == null) {
            return;
        }
        System.out.println(tree.value);
        printPreOrder(tree.left);
        printPreOrder(tree.right);
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node tree) {
        if (tree == null) {
            return;
        }
        printInOrder(tree.left);
        System.out.println(tree.value);
        printInOrder(tree.right);
    }

    public void printInReverseOrder() {
        printInReverseOrder(root);
    }

    private void printInReverseOrder(Node tree) {
        if (tree == null) {
            return;
        }
        printInReverseOrder(tree.right);
        System.out.println(tree.value);
        printInReverseOrder(tree.left);
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(Node tree) {
        if (tree == null) {
            return;
        }
        printPostOrder(tree.left);
        printPostOrder(tree.right);
        System.out.println(tree.value);

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

        @Override
        public String toString() {
            return String.format("Node(%s)", value);
        }
    }
}
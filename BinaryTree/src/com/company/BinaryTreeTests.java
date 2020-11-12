package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class BinaryTreeTests {
    @Test
    @DisplayName("Validate that printTreeInOrder prints elements in order")
    public void printTreeInOrderPrintsInOrder() {
        // Arrange
        OutputStream outputStream = createOutputListener();

        BinaryTree testTree = getTestTree();

        // Act
        testTree.printInOrder();

        // Assert
        String capturedOutput = outputStream.toString().replaceAll("\\r\\n|\\r", "\n");
        String expectedOutput = "-32\n-30\n0\n5\n12\n13\n24\n25\n30\n32\n34\n35\n439\n500\n550\n1700\n1701\n";

        Assertions.assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    @DisplayName("Validate that printTreeInReverseOrder prints elements in reverse order")
    public void printTreeInReverseOrderPrintsInReverseOrder() {
        // Arrange
        OutputStream outputStream = createOutputListener();

        BinaryTree testTree = getTestTree();

        // Act
        testTree.printInReverseOrder();

        // Assert
        String capturedOutput = outputStream.toString().replaceAll("\\r\\n|\\r", "\n");
        String expectedOutput = "1701\n1700\n550\n500\n439\n35\n34\n32\n30\n25\n24\n13\n12\n5\n0\n-30\n-32\n";

        Assertions.assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    @DisplayName("Validate that output is correct after nodes have been removed")
    public void printsCorrectsOutputAfterRemoval() {
        // Arrange
        OutputStream outputStream = createOutputListener();

        BinaryTree testTree = getTestTree();

        // Act
        testTree.remove(1700);
        testTree.remove(-32);
        testTree.remove(32);
        testTree.remove(24);
        testTree.remove(35);

        testTree.printInOrder();

        // Assert
        String capturedOutput = outputStream.toString().replaceAll("\\r\\n|\\r", "\n");
        String expectedOutput = "-30\n0\n5\n12\n13\n25\n30\n34\n439\n500\n550\n1701\n";

        Assertions.assertEquals(expectedOutput, capturedOutput);
    }

    /**
     * Creates a binary tree with the following structure:
     * <pre><code>
     *
     *                32
     *          _____/  \_____
     *        24              500
     *       /  \            /   \
     *      0    25         35   550
     *     / \    \        /  \    \
     *   -30  5    30     34  439 1701
     *   /     \                   /
     * -32      13               1700
     *          /
     *         12
     *
     * </code></pre>
     *
     * In order:
     * [-32, -30, 0, 5, 12, 13, 24, 25, 30, 32, 34, 35, 439, 500, 550, 1700, 1701]
     *
     * @return an instance of {@link BinaryTree} with the values described
     */
    private BinaryTree getTestTree() {
        // Inserting values randomly with root 32 should result in the tree above
        var tree = new BinaryTree();
        tree.insert(32);
        tree.insert(24);
        tree.insert(0);
        tree.insert(5);
        tree.insert(500);
        tree.insert(-30);
        tree.insert(35);
        tree.insert(550);
        tree.insert(25);
        tree.insert(-32);
        tree.insert(1701);
        tree.insert(13);
        tree.insert(34);
        tree.insert(30);
        tree.insert(12);
        tree.insert(1700);
        tree.insert(439);

        return tree;
    }

    private OutputStream createOutputListener() {
        var outputStream = new ByteArrayOutputStream();
        var printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        return outputStream;
    }
}
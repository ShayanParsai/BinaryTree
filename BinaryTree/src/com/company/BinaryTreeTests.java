package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class BinaryTreeTests {
    @Test
    @DisplayName("Validate that printInOrder prints elements in order")
    public void printInOrder() {
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
    @DisplayName("Validate that printInReverseOrder prints elements in reverse order")
    public void printInReverseOrder() {
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
    @DisplayName("Validate that printPreOrder prints elements pre ordered")
    public void printPreOrder() {
        // Arrange
        OutputStream outputStream = createOutputListener();

        BinaryTree testTree = getTestTree();

        // Act
        testTree.printPreOrder();

        // Assert
        String capturedOutput = outputStream.toString().replaceAll("\\r\\n|\\r", "\n");
        String expectedOutput = "32\n24\n0\n-30\n-32\n5\n13\n12\n25\n30\n500\n35\n34\n439\n550\n1701\n1700\n";

        Assertions.assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    @DisplayName("Validate that printPostOrder prints elements post ordered")
    public void printPostOrder() {
        // Arrange
        OutputStream outputStream = createOutputListener();

        BinaryTree testTree = getTestTree();

        // Act
        testTree.printPostOrder();

        // Assert
        String capturedOutput = outputStream.toString().replaceAll("\\r\\n|\\r", "\n");
        String expectedOutput = "-32\n-30\n12\n13\n5\n0\n30\n25\n24\n34\n439\n35\n1700\n1701\n550\n500\n32\n";

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
        testTree.remove(13);
        testTree.remove(1701);
        testTree.remove(34);
        testTree.remove(439);
        testTree.remove(0);
        testTree.remove(30);
        testTree.remove(25);
        testTree.remove(12);
        testTree.remove(5);
        testTree.remove(500);

        testTree.printInOrder();

        // Assert
        String capturedOutput = outputStream.toString().replaceAll("\\r\\n|\\r", "\n");
        String expectedOutput = "-30\n550\n";

        Assertions.assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    @DisplayName("KeyAlreadyExistsException is thrown when a key that exists is inserted")
    public void insertThrowsException() {
        // Arrange
        BinaryTree testTree = getTestTree();

        // Act & Assert
        Assertions.assertThrows(KeyAlreadyExistsException.class, () -> testTree.insert(439));
    }

    @Test
    @DisplayName("InvalidKeyException is thrown when an non-existent key is removed")
    public void removeThrowsException() {
        // Arrange
        BinaryTree testTree = getTestTree();

        // Act & Assert
        Assertions.assertThrows(InvalidKeyException.class, () -> testTree.remove(17));
    }

    @Test
    @DisplayName("NullPointerException is not thrown when tree is depopulated")
    public void depopulationDoesNotThrowException() {
        // Arrange
        BinaryTree testTree = getTestTree();

        // Act & Assert
        testTree.remove(32);
        testTree.remove(24);
        testTree.remove(0);
        testTree.remove(5);
        testTree.remove(500);
        testTree.remove(-30);
        testTree.remove(35);
        testTree.remove(550);
        testTree.remove(25);
        testTree.remove(-32);
        testTree.remove(1701);
        testTree.remove(13);
        testTree.remove(34);
        testTree.remove(30);
        testTree.remove(12);
        testTree.remove(1700);

        try {
            testTree.remove(439); // <- this is the last element
        } catch (NullPointerException e) {
            Assertions.fail(e);
        }
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
     * <pre>
     * In order:
     * [-32, -30, 0, 5, 12, 13, 24, 25, 30, 32, 34, 35, 439, 500, 550, 1700, 1701]
     *
     * In reverse order:
     * [1701, 1700, 550, 500, 439, 35, 34, 32, 30, 25, 24, 13, 12, 5, 0, -30, -32]
     *
     * Pre order:
     * [32, 24, 0, -30, -32, 5, 13, 12, 25, 30, 500, 35, 34, 439, 550, 1701, 1700]
     *
     * Post order:
     * [-32, -30, 12, 13, 5, 0, 30, 25, 24, 34, 439, 35, 1700, 1701, 550, 500, 32]
     *</pre>
     *
     * @return an instance of {@link BinaryTree} with the values described
     */
    private BinaryTree getTestTree() {
        // Inserting values in the order below will result in the tree described
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
package com.company;

public class BubbleSort {

    public void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        printArray(array);
    }

    private void printArray(int[] array) {
        for (int n : array) {
            System.out.print(n + " -> ");
        }
        System.out.println("\n");
    }
}

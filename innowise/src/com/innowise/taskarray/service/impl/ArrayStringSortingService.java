package com.innowise.taskarray.service.impl;

import com.innowise.taskarray.service.SortingService;

public class ArrayStringSortingService implements SortingService {
    @Override
    public String[] quickSort(String[] array) {
        if (array.length <= 1) {
            return array;
        }
        quickSortRecursive(array, 0, array.length - 1);
        return array;
    }

    private void quickSortRecursive(String[] array, int left, int right) {
        if (left >= right) return;

        int middleElemLength = array[(left + right) / 2].length();
        int i = left;
        int j = right;

        while (i <= j) {
            while (array[i].length() < middleElemLength) {
                i++;
            }
            while (array[j].length() > middleElemLength) {
                j--;
            }
            if (i <= j) {
                String temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        quickSortRecursive(array, left, j);
        quickSortRecursive(array, i, right);
    }


    @Override
    public String[] shellSort(String[] array) {
        for (int s = array.length / 2; s >= 1; s--) {
            for (int i = s; i < array.length; i++) {
                for (int j = i - s; j >= 0; j--){
                    if (array[j].length() > array[j + s].length()) {
                        String temp = array[j];
                        array[j] = array[j+s];
                        array[j+s] = temp;
                    }
                }
            }
        }
        return array;
    }

    @Override
    public String[] bubbleSort(String[] array) {
        boolean notSorted = true;
        int endSortedIndex = array.length - 1;
        while (notSorted) {
            notSorted = false;
            for (int i = 0; i < endSortedIndex; i++) {
                if (array[i].length() > array[i + 1].length()) {
                    String temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    notSorted = true;
                }
            }
            endSortedIndex--;
        }
        return array;
    }
}

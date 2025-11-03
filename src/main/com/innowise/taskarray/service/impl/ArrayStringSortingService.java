package main.com.innowise.taskarray.service.impl;

import main.com.innowise.taskarray.service.SortingService;
import main.com.innowise.taskarray.entity.ArrayEntity;

import java.util.logging.Logger;

public class ArrayStringSortingService implements SortingService {
    private static final Logger logger = Logger.getLogger(ArrayStringSortingService.class.getName());
    @Override
    public String[] quickSort(ArrayEntity array) {
        logger.info("Method quickSort start");
        String[] entityData = array.getData();
        String[] data = entityData.clone();
        if (data.length <= 1) {
            logger.fine("Data length is less than 2. Method quickSort end");
            return data;
        }
        quickSortRecursive(data, 0, data.length - 1);
        logger.info("Method quickSort end");
        return data;
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
    public String[] shellSort(ArrayEntity array) {
        logger.info("Method shellSort start");
        String[] entityData = array.getData();
        String[] data = entityData.clone();
        for (int s = data.length / 2; s >= 1; s--) {
            for (int i = s; i < data.length; i++) {
                for (int j = i - s; j >= 0; j--){
                    if (data[j].length() > data[j + s].length()) {
                        String temp = data[j];
                        data[j] = data[j+s];
                        data[j+s] = temp;
                    }
                }
            }
        }
        logger.info("Method shellSort end");
        return data;
    }

    @Override
    public String[] bubbleSort(ArrayEntity array) {
        logger.info("Method bubbleSort start");
        String[] entityData = array.getData();
        String[] data = entityData.clone();
        boolean notSorted = true;
        int endSortedIndex = data.length - 1;
        while (notSorted) {
            notSorted = false;
            for (int i = 0; i < endSortedIndex; i++) {
                if (data[i].length() > data[i + 1].length()) {
                    String temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    notSorted = true;
                }
            }
            endSortedIndex--;
        }
        logger.info("Method bubbleSort end");
        return data;
    }
}

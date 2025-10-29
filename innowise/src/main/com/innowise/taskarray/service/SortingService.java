package main.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.ArrayEntity;

public interface SortingService {
    String[] quickSort(ArrayEntity array);
    String[] shellSort(ArrayEntity array);
    String[] bubbleSort(ArrayEntity array);
}

package com.innowise.taskarray.services;

public class CountSignService {
    public int countPositive(int[] array){
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0){
                count++;
            }
        }
        return count;
    }

    public int countNegative(int[] array){
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0){
                count++;
            }
        }
        return count;
    }
}

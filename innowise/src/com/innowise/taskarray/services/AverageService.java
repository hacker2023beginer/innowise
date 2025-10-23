package com.innowise.taskarray.services;

import java.util.Arrays;

public class AverageService {
    public int average(int[] array){
        int average = 0;
        for (int i = 0; i < array.length; i++) {
            average += array[i];
        }
        return average / array.length;
    }
}

package com.innowise.taskarray.service.impl;

import com.innowise.taskarray.service.AverageService;

public class ArrayStringAverageService implements AverageService {
    @Override
    public int average(String[] array){
        int sum = 0;
        for (String string : array){
            sum += string.length();
        }
        return sum / array.length;
    }
}

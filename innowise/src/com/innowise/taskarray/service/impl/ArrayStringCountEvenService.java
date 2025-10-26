package com.innowise.taskarray.service.impl;

import com.innowise.taskarray.service.CountEvenService;

public class ArrayStringCountEvenService implements CountEvenService {
    @Override
    public int countEven(String[] array){
        int count = 0;
        for (String string : array) {
            if (string.length() % 2 == 0){
                count++;
            }
        }
        return count;
    }

    @Override
    public int countOdd(String[] array){
        int count = 0;
        for (String string : array) {
            if (string.length() % 2 == 1){
                count++;
            }
        }
        return count;
    }
}

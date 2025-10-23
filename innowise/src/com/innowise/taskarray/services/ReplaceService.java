package com.innowise.taskarray.services;

import java.util.function.IntPredicate;

public class ReplaceService {
    public int[] replaceIf(int[] array, IntPredicate predicate, int value){
        int[] result = new int[array.length];
        for(int i = 0; i < array.length; i++){
            if(predicate.test(array[i])){
                result[i] = array[i];
            }
        }
        return result;
    }
}

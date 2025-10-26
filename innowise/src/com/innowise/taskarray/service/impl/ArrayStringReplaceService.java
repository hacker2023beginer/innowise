package com.innowise.taskarray.service.impl;

import com.innowise.taskarray.service.ReplaceService;

import java.util.function.IntPredicate;

public class ArrayStringReplaceService implements ReplaceService {
    @Override
    public String[] replaceIf(String[] array, IntPredicate predicate, String value){
        for(int i = 0; i < array.length; i++){
            if(predicate.test(array[i].length())){
                array[i] = value;
            }
        }
        return array;
    }
}

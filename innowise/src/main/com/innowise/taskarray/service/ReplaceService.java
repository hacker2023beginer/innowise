package main.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;

import java.util.function.IntPredicate;

public interface ReplaceService {
    String[] replaceIf(ArrayEntity array, IntPredicate predicate, String value);
}

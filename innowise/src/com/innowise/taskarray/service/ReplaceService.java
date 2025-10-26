package com.innowise.taskarray.service;

import java.util.function.IntPredicate;

public interface ReplaceService {
    String[] replaceIf(String[] array, IntPredicate predicate, String value);
}

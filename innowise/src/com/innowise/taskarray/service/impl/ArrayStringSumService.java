package com.innowise.taskarray.service.impl;

import com.innowise.taskarray.service.SumService;

public class ArrayStringSumService implements SumService {
    @Override
    public int sum(String string1, String string2) {
        return string1.length() + string2.length();
    }
}

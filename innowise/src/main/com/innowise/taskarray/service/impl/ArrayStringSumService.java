package main.com.innowise.taskarray.service.impl;

import main.com.innowise.taskarray.service.SumService;

import java.util.logging.Logger;

public class ArrayStringSumService implements SumService {
    private static final Logger logger = Logger.getLogger(ArrayStringSumService.class.getName());
    @Override
    public int sum(String string1, String string2) {
        return string1.length() + string2.length();
    }
}

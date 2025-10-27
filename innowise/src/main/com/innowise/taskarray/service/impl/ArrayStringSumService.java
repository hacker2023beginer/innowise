package main.com.innowise.taskarray.service.impl;

import main.com.innowise.taskarray.service.SumService;

import java.util.logging.Logger;

public class ArrayStringSumService implements SumService {
    private static final Logger logger = Logger.getLogger(ArrayStringSumService.class.getName());
    @Override
    public int sum(String string1, String string2) {
        logger.info("Method sum start");
        int sum = 0;
        logger.info("String 1: " + string1);
        logger.info("String 2: " + string2);
        sum = string1.length() + string2.length();
        logger.info("Sum: " + sum);
        return sum;
    }
}

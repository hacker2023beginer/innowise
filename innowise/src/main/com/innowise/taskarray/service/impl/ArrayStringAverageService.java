package main.com.innowise.taskarray.service.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.service.AverageService;

import java.util.logging.Logger;

public class ArrayStringAverageService implements AverageService {
    private static final Logger logger = Logger.getLogger(ArrayStringAverageService.class.getName());

    @Override
    public double average(ArrayEntity array){
        logger.info("Method average start");
        double sum = 0;
        String[] data = array.getData();
        for (String string : data) {
            logger.info("String: " + string);
            logger.info("Old sum: " + sum);
            sum += string.length();
            logger.info("New sum: " + sum);
        }
        logger.info("Method average end");
        return sum / data.length;
    }
}

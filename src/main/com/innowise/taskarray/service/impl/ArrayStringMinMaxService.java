package main.com.innowise.taskarray.service.impl;

import main.com.innowise.taskarray.service.MinMaxService;
import main.com.innowise.taskarray.entity.ArrayEntity;

import java.util.logging.Logger;

public class ArrayStringMinMaxService implements MinMaxService {
    private static final Logger logger = Logger.getLogger(ArrayStringMinMaxService.class.getName());

    @Override
    public int min(ArrayEntity array) {
        logger.info("Method min start");
        String[] data = array.getData();
        int min = data[0].length();
        int currentStringLength = 0;
        for (String string : data) {
            currentStringLength = string.length();
            if (min > currentStringLength) {
                logger.fine("Founded new min: " + currentStringLength);
                min = currentStringLength;
            }
        }
        logger.info("Method min end");
        return min;
    }

    @Override
    public int max(ArrayEntity array) {
        logger.info("Method max start");
        String[] data = array.getData();
        int max = 0;
        int currentStringLength = 0;
        for (String string : data) {
            currentStringLength = string.length();
            if (max < currentStringLength) {
                logger.fine("Founded new max: " + currentStringLength);
                max = currentStringLength;
            }
        }
        logger.info("Method max end");
        return max;
    }

}

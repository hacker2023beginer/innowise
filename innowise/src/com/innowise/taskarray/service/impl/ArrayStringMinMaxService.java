package com.innowise.taskarray.service.impl;

import com.innowise.taskarray.service.MinMaxService;
import java.util.logging.Logger;

public class ArrayStringMinMaxService implements MinMaxService {
    private static final Logger logger = Logger.getLogger(ArrayStringMinMaxService.class.getName());
    @Override
    public int min(String[] array) {
        logger.info("Метод min начал работу");
        int min = array[0].length();
        int currentStringLength = 0;
        for (String string : array) {
            currentStringLength = string.length();
            if (min > currentStringLength) {
                logger.fine("Найден новый минимум: " + currentStringLength);
                min = currentStringLength;
            }
        }

        return min;
    }

    @Override
    public int max(String[] array) {
        int max = 0;
        int currentStringLength = 0;
        for (String string : array) {
            currentStringLength = string.length();
            if (max < currentStringLength) {
                max = currentStringLength;
            }
        }
        return max;
    }

}

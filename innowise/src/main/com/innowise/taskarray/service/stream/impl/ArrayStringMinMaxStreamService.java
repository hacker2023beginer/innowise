package main.com.innowise.taskarray.service.stream.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.service.MinMaxService;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ArrayStringMinMaxStreamService implements MinMaxService {
    private static final Logger logger = Logger.getLogger(ArrayStringMinMaxStreamService.class.getName());
    @Override
    public int min(ArrayEntity array) {
        logger.info("Method min() called with ArrayEntity id=" + array.getId());
        logger.fine("Raw data: " + Arrays.toString(array.getData()));

        Stream<String> stream = Arrays.stream(array.getData());
        int min = stream
                .mapToInt(String::length)
                .peek(len -> logger.fine("Token length: " + len))
                .min()
                .getAsInt();

        logger.info("Minimum token length: " + min);
        return min;
    }

    @Override
    public int max(ArrayEntity array) {
        logger.info("Method max() called with ArrayEntity id=" + array.getId());
        logger.fine("Raw data: " + Arrays.toString(array.getData()));

        Stream<String> stream = Arrays.stream(array.getData());
        int max = stream
                .mapToInt(String::length)
                .peek(len -> logger.fine("Token length: " + len))
                .max()
                .getAsInt();

        logger.info("Maximum token length: " + max);
        return max;
    }

}

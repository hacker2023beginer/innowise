package main.com.innowise.taskarray.service.stream.impl;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.service.AverageService;
import main.com.innowise.taskarray.service.impl.ArrayStringAverageService;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ArrayStringAverageStreamService implements AverageService {
    private static final Logger logger = Logger.getLogger(ArrayStringAverageStreamService.class.getName());

    @Override
    public double average(ArrayEntity array) {
        logger.info("Method average() called with ArrayEntity id=" + array.getId());

        Stream<String> stream = Arrays.stream(array.getData());
        logger.fine("Raw data: " + Arrays.toString(array.getData()));

        double result = stream
                .mapToInt(String::length)
                .peek(len -> logger.fine("Token length: " + len))
                .average()
                .orElse(0);

        logger.info("Computed average length: " + result);
        return result;
    }
}

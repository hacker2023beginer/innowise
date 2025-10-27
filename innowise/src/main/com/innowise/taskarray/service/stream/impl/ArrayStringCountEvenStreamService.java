package main.com.innowise.taskarray.service.stream.impl;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.service.CountEvenService;
import main.com.innowise.taskarray.service.impl.ArrayStringAverageService;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ArrayStringCountEvenStreamService implements CountEvenService {
    private static final Logger logger = Logger.getLogger(ArrayStringCountEvenStreamService.class.getName());
    @Override
    public int countEven(ArrayEntity array) {
        logger.info("Method countEven() called with ArrayEntity id=" + array.getId());
        logger.fine("Raw data: " + Arrays.toString(array.getData()));

        Stream<String> stream = Arrays.stream(array.getData());
        int count = (int) stream
                .mapToInt(String::length)
                .peek(len -> logger.fine("Token length: " + len))
                .filter(length -> length % 2 == 0)
                .count();

        logger.info("Even-length token count: " + count);
        return count;
    }


    @Override
    public int countOdd(ArrayEntity array) {
        logger.info("Method countOdd() called with ArrayEntity id=" + array.getId());
        logger.fine("Raw data: " + Arrays.toString(array.getData()));

        Stream<String> stream = Arrays.stream(array.getData());
        int count = (int) stream
                .mapToInt(String::length)
                .peek(len -> logger.fine("Token length: " + len))
                .filter(length -> length % 2 != 0)
                .count();

        logger.info("Odd-length token count: " + count);
        return count;
    }
}

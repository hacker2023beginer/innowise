package main.com.innowise.taskarray.service.stream.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.service.ReplaceService;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ArrayStringReplaceStreamService implements ReplaceService {
    private static final Logger logger = Logger.getLogger(ArrayStringReplaceStreamService.class.getName());

    @Override
    public String[] replaceIf(ArrayEntity array, IntPredicate predicate, String value) {
        logger.info("Method replaceIf() called with ArrayEntity id=" + array.getId());
        logger.fine("Raw data: " + Arrays.toString(array.getData()));
        logger.fine("Replacement value: \"" + value + "\"");

        Stream<String> stream = Arrays.stream(array.getData().clone());
        String[] result = stream
                .map(s -> {
                    int len = s.length();
                    boolean shouldReplace = predicate.test(len);
                    logger.fine("Token: \"" + s + "\", length=" + len + ", replaced=" + shouldReplace);
                    return shouldReplace ? value : s;
                })
                .toArray(String[]::new);

        logger.info("replaceIf() completed. Result: " + Arrays.toString(result));
        return result;
    }
}

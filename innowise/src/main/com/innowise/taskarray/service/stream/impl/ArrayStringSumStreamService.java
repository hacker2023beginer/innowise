package main.com.innowise.taskarray.service.stream.impl;

import main.com.innowise.taskarray.service.SumService;

import java.util.logging.Logger;
import java.util.stream.Stream;

public class ArrayStringSumStreamService implements SumService {
    private static final Logger logger = Logger.getLogger(ArrayStringSumStreamService.class.getName());

    @Override
    public int sum(String string1, String string2) {
        logger.info("Method sum() called with inputs: string1=\"" + string1 + "\", string2=\"" + string2 + "\"");

        int result = Stream.of(string1, string2)
                .filter(obj -> {
                    boolean isValid = obj != null;
                    logger.fine("Filtering: \"" + obj + "\" → valid=" + isValid);
                    return isValid;
                })
                .mapToInt(s -> {
                    int len = s.length();
                    logger.fine("Mapping: \"" + s + "\" → length=" + len);
                    return len;
                })
                .sum();

        logger.info("Computed sum of lengths: " + result);
        return result;
    }


}

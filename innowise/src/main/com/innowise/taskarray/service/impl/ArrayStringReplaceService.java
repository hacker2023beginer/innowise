package main.com.innowise.taskarray.service.impl;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.service.ReplaceService;

import java.util.function.IntPredicate;
import java.util.logging.Logger;

public class ArrayStringReplaceService implements ReplaceService {
    private static final Logger logger = Logger.getLogger(ArrayStringReplaceService.class.getName());
    @Override
    public String[] replaceIf(ArrayEntity array, IntPredicate predicate, String value) {
        logger.info("Method replaceIf start");
        String[] entityData = array.getData();
        String[] data = entityData.clone();
        for (int i = 0; i < data.length; i++) {
            logger.info("Current data: " + data[i]);
            if (predicate.test(data[i].length())) {
                logger.fine("Old data: " + data[i]);
                data[i] = value;
                logger.fine("New data: " + data[i]);
            }
        }
        logger.info("Method replaceIf end");
        return data;
    }
}

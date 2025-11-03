package main.com.innowise.taskarray.service.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.service.CountEvenService;

import java.util.logging.Logger;

public class ArrayStringCountEvenService implements CountEvenService {
    private static final Logger logger = Logger.getLogger(ArrayStringCountEvenService.class.getName());
    @Override
    public int countEven(ArrayEntity array){
        logger.info("Method countEven start");
        int count = 0;
        String[] data = array.getData();
        for (String string : data) {
            logger.info(string);
            if (string.length() % 2 == 0){
                logger.fine("String was accepted. Old count: " + count);
                count++;
                logger.fine("New count: " + count);
            }
        }
        logger.info("Method countEven end");
        return count;
    }

    @Override
    public int countOdd(ArrayEntity array){
        logger.info("Method countOdd start");
        int count = 0;
        String[] data = array.getData();
        for (String string : data) {
            logger.info(string);
            if (string.length() % 2 == 1){
                logger.fine("String was accepted. Old count: " + count);
                count++;
                logger.fine("New count: " + count);
            }
        }
        logger.info("Method countEven end");
        return count;
    }
}

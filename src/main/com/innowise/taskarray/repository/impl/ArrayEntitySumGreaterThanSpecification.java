package main.com.innowise.taskarray.repository.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.repository.EntitySpecification;

import java.util.Arrays;
import java.util.logging.Logger;

public class ArrayEntitySumGreaterThanSpecification implements EntitySpecification {
    private static final Logger logger = Logger.getLogger(ArrayEntitySumGreaterThanSpecification.class.getName());
    private final int threshold;

    public ArrayEntitySumGreaterThanSpecification(int threshold) {
        this.threshold = threshold;
        logger.info("Specification created with threshold=" + threshold);
    }

    @Override
    public boolean specify(ArrayEntity entity) {
        String[] data = entity.getData();
        logger.fine("Evaluating entity id=" + entity.getId() + " with data=" + Arrays.toString(data));

        int sum = Arrays.stream(data)
                .mapToInt(Integer::parseInt)
                .sum();

        boolean result = sum > threshold;
        logger.fine("Computed sum=" + sum + ", threshold=" + threshold + ", passed=" + result);

        return result;
    }
}

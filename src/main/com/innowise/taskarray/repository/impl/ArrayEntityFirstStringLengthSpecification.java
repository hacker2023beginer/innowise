package main.com.innowise.taskarray.repository.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.repository.EntitySpecification;

import java.util.logging.Logger;

public class ArrayEntityFirstStringLengthSpecification implements EntitySpecification {
    private static final Logger logger = Logger.getLogger(ArrayEntityFirstStringLengthSpecification.class.getName());
    private final int targetLength;

    public ArrayEntityFirstStringLengthSpecification(int targetLength) {
        this.targetLength = targetLength;
        logger.info("Specification created with targetLength=" + targetLength);
    }

    @Override
    public boolean specify(ArrayEntity entity) {
        String[] data = entity.getData();
        String first = data[0];
        int length = first.length();
        boolean result = length == targetLength;

        logger.fine("Entity id=" + entity.getId() +
                ", first token=\"" + first + "\"" +
                ", length=" + length +
                ", matches=" + result);

        return result;
    }
}

package main.com.innowise.taskarray.repository.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.repository.EntitySpecification;

import java.util.logging.Logger;

public class ArrayEntityIdSpecification implements EntitySpecification {
    private static final Logger logger = Logger.getLogger(ArrayEntityIdSpecification.class.getName());
    private final int targetId;

    public ArrayEntityIdSpecification(int targetId) {
        this.targetId = targetId;
        logger.info("ArrayEntityIdSpecification created with targetId=" + targetId);
    }

    @Override
    public boolean specify(ArrayEntity entity) {
        int actualId = entity.getId();
        boolean result = actualId == targetId;

        logger.fine("Evaluating entity with id=" + actualId +
                " against targetId=" + targetId +
                " → match=" + result);

        return result;
    }
}

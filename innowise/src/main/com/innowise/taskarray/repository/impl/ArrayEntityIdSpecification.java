package main.com.innowise.taskarray.repository.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.repository.EntitySpecification;

public class ArrayEntityIdSpecification implements EntitySpecification {
    private final int targetId;

    public ArrayEntityIdSpecification(int targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean specify(ArrayEntity entity) {
        return entity.getId() == targetId;
    }

}

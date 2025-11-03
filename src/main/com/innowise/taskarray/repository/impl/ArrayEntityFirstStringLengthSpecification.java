package main.com.innowise.taskarray.repository.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.repository.EntitySpecification;

public class ArrayEntityFirstStringLengthSpecification implements EntitySpecification {
    private final int targetLength;

    public ArrayEntityFirstStringLengthSpecification(int targetLength) {
        this.targetLength = targetLength;
    }

    @Override
    public boolean specify(ArrayEntity entity) {
        return entity.getData()[0].length() == targetLength;
    }
}

package main.com.innowise.taskarray.repository.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.repository.EntitySpecification;

import java.util.Arrays;

public class ArrayEntitySumGreaterThanSpecification implements EntitySpecification {
    private final int threshold;

    public ArrayEntitySumGreaterThanSpecification(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean specify(ArrayEntity entity) {
        int sum = Arrays.stream(entity.getData()).mapToInt(Integer::parseInt).sum();
        return sum > threshold;
    }
}

package main.com.innowise.taskarray.repository;

import main.com.innowise.taskarray.entity.ArrayEntity;

public interface EntitySpecification {
    boolean specify(ArrayEntity entity);
}

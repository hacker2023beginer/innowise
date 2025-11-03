package main.com.innowise.taskarray.observer;

import main.com.innowise.taskarray.entity.ArrayEntity;

public interface ArrayObserver {
    void onArrayChanged(ArrayEntity entity);
}

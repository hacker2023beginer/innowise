package main.com.innowise.taskarray.observer.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.observer.ArrayObserver;
import main.com.innowise.taskarray.warehouse.ArrayEntityWarehouse;
import main.com.innowise.taskarray.entity.ArrayParameters;

public class ArrayEntityObserver implements ArrayObserver {
    private final ArrayEntityWarehouse warehouse = ArrayEntityWarehouse.getInstance();

    @Override
    public void onArrayChanged(ArrayEntity entity) {
        ArrayParameters parameters = new ArrayParameters(entity.getData());
        warehouse.put(entity.getId(), parameters);
    }
}

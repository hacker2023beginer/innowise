package main.com.innowise.taskarray.observer.impl;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.entity.ArrayParameters;
import main.com.innowise.taskarray.observer.ArrayObserver;
import main.com.innowise.taskarray.warehouse.ArrayEntityWarehouse;

import java.util.Arrays;
import java.util.logging.Logger;

public class ArrayEntityObserver implements ArrayObserver {
    private static final Logger logger = Logger.getLogger(ArrayEntityObserver.class.getName());
    private final ArrayEntityWarehouse warehouse = ArrayEntityWarehouse.getInstance();

    @Override
    public void onArrayChanged(ArrayEntity entity) {
        logger.info("onArrayChanged called for entity id=" + entity.getId());
        logger.fine("Raw data: " + Arrays.toString(entity.getData()));

        ArrayParameters parameters = new ArrayParameters(entity.getData());

        logger.fine("Computed parameters: sum=" + parameters.getSum() +
                ", avg=" + parameters.getAverage() +
                ", min=" + parameters.getMin() +
                ", max=" + parameters.getMax());

        warehouse.put(entity.getId(), parameters);
        logger.info("Parameters stored in warehouse for id=" + entity.getId());
    }
}

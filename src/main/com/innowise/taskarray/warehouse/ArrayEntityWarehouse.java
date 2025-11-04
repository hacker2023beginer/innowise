package main.com.innowise.taskarray.warehouse;

import main.com.innowise.taskarray.entity.ArrayParameters;
import main.com.innowise.taskarray.reader.impl.ArrayStringFileReader;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ArrayEntityWarehouse {
    private static final Logger logger = Logger.getLogger(ArrayEntityWarehouse.class.getName());
    private static ArrayEntityWarehouse instance;
    private final Map<Integer, ArrayParameters> arrayEntityParametersMap = new HashMap<>();

    private ArrayEntityWarehouse() {
        logger.info("ArrayEntityWarehouse initialized");
    }

    public static ArrayEntityWarehouse getInstance() {
        if (instance == null) {
            logger.info("Creating new ArrayEntityWarehouse instance");
            instance = new ArrayEntityWarehouse();
        }
        logger.fine("Returning ArrayEntityWarehouse instance");
        return instance;
    }

    public void put(int id, ArrayParameters parameters) {
        logger.info("Putting parameters for id=" + id);
        logger.fine("Parameters: sum=" + parameters.getSum() +
                ", avg=" + parameters.getAverage() +
                ", min=" + parameters.getMin() +
                ", max=" + parameters.getMax());
        arrayEntityParametersMap.put(id, parameters);
    }

    public ArrayParameters get(int id) {
        logger.info("Getting parameters for id=" + id);
        ArrayParameters result = arrayEntityParametersMap.get(id);
        if (result == null) {
            logger.warning("No parameters found for id=" + id);
            return result;
        }
        logger.fine("Retrieved: sum=" + result.getSum() +
                ", avg=" + result.getAverage() +
                ", min=" + result.getMin() +
                ", max=" + result.getMax());
        return result;
    }
}

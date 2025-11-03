package main.com.innowise.taskarray.warehouse;

import main.com.innowise.taskarray.entity.ArrayParameters;

import java.util.HashMap;
import java.util.Map;

public class ArrayEntityWarehouse {
    private static ArrayEntityWarehouse instance;
    private final Map<Integer, ArrayParameters> arrayEntityParametersMap = new HashMap<>();

    private ArrayEntityWarehouse() {}

    public static ArrayEntityWarehouse getInstance() {
        if (instance == null) {
            instance = new ArrayEntityWarehouse();
        }
        return instance;
    }

    public void put(int id, ArrayParameters parameters) {
        arrayEntityParametersMap.put(id, parameters);
    }

    public ArrayParameters get(int id) {
        return arrayEntityParametersMap.get(id);
    }
}

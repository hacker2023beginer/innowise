package main.com.innowise.taskarray.observer;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.entity.ArrayParameters;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.observer.impl.ArrayEntityObserver;
import main.com.innowise.taskarray.warehouse.ArrayEntityWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntityObserverTest {

    private ArrayEntityWarehouse warehouse;
    private ArrayEntityObserver observer;

    @BeforeEach
    public void setup() {
        warehouse = ArrayEntityWarehouse.getInstance();
        observer = new ArrayEntityObserver();
    }

    private ArrayEntity buildEntity(int id, String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(id)
                .setData(data)
                .build();
    }

    @Test
    public void testObserverUpdatesWarehouseCorrectly() throws ArrayException {
        ArrayEntity entity = buildEntity(42, "abc", "de", "fghi"); // lengths: 3, 2, 4
        observer.onArrayChanged(entity);

        ArrayParameters params = warehouse.get(42);

        assertAll(
                () -> assertNotNull(params, "Parameters should be stored in warehouse"),
                () -> assertEquals(9, params.getSum(), "Sum should be 3+2+4 = 9"),
                () -> assertEquals(3.0, params.getAverage(), "Average should be 9 / 3 = 3.0"),
                () -> assertEquals(4, params.getMax(), "Max length should be 4"),
                () -> assertEquals(2, params.getMin(), "Min length should be 2")
        );
    }
}

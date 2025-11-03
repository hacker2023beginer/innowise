package main.com.innowise.taskarray.warehouse;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.entity.ArrayParameters;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.observer.impl.ArrayEntityObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntityWarehouseTest {

    private ArrayEntityWarehouse warehouse;

    @BeforeEach
    public void setup() {
        warehouse = ArrayEntityWarehouse.getInstance();
    }

    @Test
    public void testPutAndGetParameters() {
        String[] data = {"abc", "de", "fghi"}; // lengths: 3, 2, 4 → sum=9, avg=3.0, min=2, max=4
        ArrayParameters params = new ArrayParameters(data);
        warehouse.put(101, params);

        ArrayParameters stored = warehouse.get(101);
        assertNotNull(stored, "Parameters should be stored");

        assertAll(
                () -> assertEquals(9, stored.getSum()),
                () -> assertEquals(3.0, stored.getAverage()),
                () -> assertEquals(2, stored.getMin()),
                () -> assertEquals(4, stored.getMax())
        );
    }

    @Test
    public void testGetReturnsNullForUnknownId() {
        ArrayParameters result = warehouse.get(999);
        assertNull(result, "Should return null for unknown ID");
    }

    @Test
    public void testSingletonInstanceIsConsistent() {
        ArrayEntityWarehouse another = ArrayEntityWarehouse.getInstance();
        assertSame(warehouse, another, "Both references should point to the same singleton instance");
    }

    @Test
    public void testObserverUpdatesWarehouseCorrectly() throws ArrayException {
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setId(777)
                .setData(new String[]{"abc", "de", "fghi"}) // lengths: 3, 2, 4
                .build();

        ArrayEntityObserver observer = new ArrayEntityObserver();
        observer.onArrayChanged(entity);

        ArrayParameters params = ArrayEntityWarehouse.getInstance().get(777);
        assertNotNull(params);

        assertAll(
                () -> assertEquals(9, params.getSum()),
                () -> assertEquals(3.0, params.getAverage()),
                () -> assertEquals(2, params.getMin()),
                () -> assertEquals(4, params.getMax())
        );
    }

}

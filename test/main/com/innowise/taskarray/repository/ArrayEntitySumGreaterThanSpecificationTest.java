package main.com.innowise.taskarray.repository;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.repository.impl.ArrayEntitySumGreaterThanSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntitySumGreaterThanSpecificationTest {

    private ArrayEntity buildEntity(String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(1)
                .setData(data)
                .build();
    }

    @Test
    public void testSpecificationMatchesWhenSumIsGreater() throws ArrayException {
        ArrayEntity entity = buildEntity("10", "20", "30"); // sum = 60
        ArrayEntitySumGreaterThanSpecification spec = new ArrayEntitySumGreaterThanSpecification(50);

        assertTrue(spec.specify(entity), "Should match when sum > threshold");
    }

    @Test
    public void testSpecificationDoesNotMatchWhenSumIsEqual() throws ArrayException {
        ArrayEntity entity = buildEntity("10", "20", "30"); // sum = 60
        ArrayEntitySumGreaterThanSpecification spec = new ArrayEntitySumGreaterThanSpecification(60);

        assertFalse(spec.specify(entity), "Should not match when sum == threshold");
    }

    @Test
    public void testSpecificationDoesNotMatchWhenSumIsLess() throws ArrayException {
        ArrayEntity entity = buildEntity("5", "10"); // sum = 15
        ArrayEntitySumGreaterThanSpecification spec = new ArrayEntitySumGreaterThanSpecification(20);

        assertFalse(spec.specify(entity), "Should not match when sum < threshold");
    }

    @Test
    public void testSpecificationWorksWithNegativeNumbers() throws ArrayException {
        ArrayEntity entity = buildEntity("-10", "5", "20"); // sum = 15
        ArrayEntitySumGreaterThanSpecification spec = new ArrayEntitySumGreaterThanSpecification(10);

        assertTrue(spec.specify(entity), "Should match with negative values included");
    }
}

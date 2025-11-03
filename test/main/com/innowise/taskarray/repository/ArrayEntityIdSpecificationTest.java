package main.com.innowise.taskarray.repository;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.repository.impl.ArrayEntityIdSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntityIdSpecificationTest {

    private ArrayEntity buildEntity(int id, String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(id)
                .setData(data)
                .build();
    }

    @Test
    public void testSpecificationMatchesCorrectId() throws ArrayException {
        ArrayEntity entity = buildEntity(42, "a", "b");
        ArrayEntityIdSpecification spec = new ArrayEntityIdSpecification(42);

        assertTrue(spec.specify(entity), "Should match when ID equals target");
    }

    @Test
    public void testSpecificationDoesNotMatchIncorrectId() throws ArrayException {
        ArrayEntity entity = buildEntity(99, "x", "y");
        ArrayEntityIdSpecification spec = new ArrayEntityIdSpecification(42);

        assertFalse(spec.specify(entity), "Should not match when ID differs from target");
    }

    @Test
    public void testSpecificationWorksWithNegativeId() throws ArrayException {
        ArrayEntity entity = buildEntity(-1, "abc");
        ArrayEntityIdSpecification spec = new ArrayEntityIdSpecification(-1);

        assertTrue(spec.specify(entity), "Should match when negative ID equals target");
    }
}

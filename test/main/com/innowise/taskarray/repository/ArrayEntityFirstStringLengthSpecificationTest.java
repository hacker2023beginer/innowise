package main.com.innowise.taskarray.repository;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.repository.impl.ArrayEntityFirstStringLengthSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntityFirstStringLengthSpecificationTest {

    private ArrayEntity buildEntity(String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(1)
                .setData(data)
                .build();
    }

    @Test
    public void testSpecificationMatchesCorrectLength() throws ArrayException {
        ArrayEntity entity = buildEntity("abc", "def", "ghi"); // first string length = 3
        ArrayEntityFirstStringLengthSpecification spec = new ArrayEntityFirstStringLengthSpecification(3);

        assertTrue(spec.specify(entity), "Should match when first string length equals target");
    }

    @Test
    public void testSpecificationDoesNotMatchIncorrectLength() throws ArrayException {
        ArrayEntity entity = buildEntity("abcd", "x"); // first string length = 4
        ArrayEntityFirstStringLengthSpecification spec = new ArrayEntityFirstStringLengthSpecification(3);

        assertFalse(spec.specify(entity), "Should not match when first string length differs from target");
    }
}

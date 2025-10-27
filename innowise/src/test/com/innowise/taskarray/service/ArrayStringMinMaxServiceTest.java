package test.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.impl.ArrayStringMinMaxService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringMinMaxServiceTest {

    private final ArrayStringMinMaxService service = new ArrayStringMinMaxService();

    @Test
    void testMinWithMixedLengths() throws ArrayException {
        String[] input = {"abc", "a", "abcdef", "xyz"}; // lengths: 3, 1, 6, 3 → min = 1
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(1)
                .build();

        int result = service.min(entity);
        assertEquals(1, result);
    }

    @Test
    void testMaxWithMixedLengths() throws ArrayException {
        String[] input = {"abc", "a", "abcdef", "xyz"}; // max = 6
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(2)
                .build();

        int result = service.max(entity);
        assertEquals(6, result);
    }

    @Test
    void testMinMaxWithEqualLengths() throws ArrayException {
        String[] input = {"aa", "bb", "cc"}; // all length = 2
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(3)
                .build();

        assertEquals(2, service.min(entity));
        assertEquals(2, service.max(entity));
    }

    @Test
    void testMinMaxWithSingleElement() throws ArrayException {
        String[] input = {"abcdef"}; // length = 6
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(4)
                .build();

        assertEquals(6, service.min(entity));
        assertEquals(6, service.max(entity));
    }

    @Test
    void testMinMaxWithEmptyArrayThrowsException() {
        String[] input = {};
        assertThrows(ArrayException.class, () ->
                ArrayEntity.newBuilder()
                        .setData(input)
                        .setId(5)
                        .build()
        );
    }

    @Test
    void testMinMaxWithAllInvalidTokensThrowsException() {
        String[] input = {"!!!", "", null};
        assertThrows(ArrayException.class, () ->
                ArrayEntity.newBuilder()
                        .setData(input)
                        .setId(6)
                        .build()
        );
    }
}

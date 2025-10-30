package test.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.impl.ArrayStringAverageService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringAverageServiceTest {

    private final ArrayStringAverageService service = new ArrayStringAverageService();

    @Test
    public void testAverageWithValidStrings() throws ArrayException {
        String[] input = {"abc", "de", "fghi"}; // lengths: 3, 2, 4 → sum = 9 → avg = 3.0
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(1)
                .build();

        double result = service.average(entity);
        assertEquals(3.0, result);
    }

    @Test
    public void testAverageWithEmptyStrings() throws ArrayException {
        String[] input = {"", "", ""};
        assertThrows(ArrayException.class, () ->
                ArrayEntity.newBuilder()
                        .setData(input)
                        .setId(2)
                        .build()
        );
    }

    @Test
    public void testAverageWithMixedLengthStrings() throws ArrayException {
        String[] input = {"a", "bb", "ccc", "dddd"}; // sum = 10 → avg = 2.5
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(3)
                .build();

        double result = service.average(entity);
        assertEquals(2.5, result);
    }

    @Test
    public void testAverageWithNullInsideArray() throws ArrayException {
        String[] input = {"abc", null, "de"};
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(4)
                .build();

        double result = service.average(entity);
        assertEquals(2.5, result, 0.01, "Not equal");
    }

    @Test
    public void testAverageWithSingleElement() throws ArrayException {
        String[] input = {"abcdef"}; // length = 6 → avg = 6.0
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(5)
                .build();

        double result = service.average(entity);
        assertEquals(6.0, result);
    }
}

package main.com.innowise.taskarray.service.stream;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.stream.impl.ArrayStringAverageStreamService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringAverageStreamServiceTest {

    private final ArrayStringAverageStreamService service = new ArrayStringAverageStreamService();

    @Test
    public void testAverageWithMultipleTokens() throws ArrayException {
        String[] data = {"abc", "de", "fghi"}; // lengths: 3, 2, 4 → avg = 3.0
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setId(1)
                .setData(data)
                .build();

        double result = service.average(entity);
        assertEquals(3.0, result);
    }

    @Test
    public void testAverageWithSingleToken() throws ArrayException {
        String[] data = {"abcdef"}; // length: 6 → avg = 6.0
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setId(2)
                .setData(data)
                .build();

        double result = service.average(entity);
        assertEquals(6.0, result);
    }
}


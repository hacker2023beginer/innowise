package test.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.impl.ArrayStringCountEvenService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringCountEvenServiceTest {

    private final ArrayStringCountEvenService service = new ArrayStringCountEvenService();

    @Test
    public void testCountEvenWithMixedLengths() throws ArrayException {
        String[] input = {"ab", "abc", "abcd", "a"}; // lengths: 2, 3, 4, 1 → even: ab, abcd → count = 2
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(1)
                .build();

        int result = service.countEven(entity);
        assertEquals(2, result);
    }

    @Test
    public void testCountOddWithMixedLengths() throws ArrayException {
        String[] input = {"ab", "abc", "abcd", "a"}; // odd: abc, a → count = 2
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(2)
                .build();

        int result = service.countOdd(entity);
        assertEquals(2, result);
    }

    @Test
    public void testCountEvenWithAllEvenLengths() throws ArrayException {
        String[] input = {"aa", "bbbb", "cc"}; // lengths: 2, 4, 2 → all even → count = 3
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(3)
                .build();

        int result = service.countEven(entity);
        assertEquals(3, result);
    }

    @Test
    public void testCountOddWithAllOddLengths() throws ArrayException {
        String[] input = {"a", "bbb", "ccc"}; // lengths: 1, 3, 3 → all odd → count = 3
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(4)
                .build();

        int result = service.countOdd(entity);
        assertEquals(3, result);
    }

    @Test
    public void testCountEvenWithEmptyArrayThrowsException() {
        String[] input = {};
        assertThrows(ArrayException.class, () ->
                ArrayEntity.newBuilder()
                        .setData(input)
                        .setId(5)
                        .build()
        );
    }

    @Test
    public void testCountEvenWithAllInvalidTokensThrowsException() {
        String[] input = {"!!!", "", null};
        assertThrows(ArrayException.class, () ->
                ArrayEntity.newBuilder()
                        .setData(input)
                        .setId(6)
                        .build()
        );
    }
}

package main.com.innowise.taskarray.service.stream;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.stream.impl.ArrayStringCountEvenStreamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringCountEvenStreamServiceTest {

    private ArrayStringCountEvenStreamService service;

    @BeforeEach
    public void setup() {
        service = new ArrayStringCountEvenStreamService();
    }

    private ArrayEntity buildEntity(String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(1)
                .setData(data)
                .build();
    }

    @Test
    public void testCountEvenWithMixedLengths() throws ArrayException {
        ArrayEntity entity = buildEntity("ab", "abc", "abcd", "x"); // lengths: 2, 3, 4, 1 → even: 2, 4
        int evenCount = service.countEven(entity);
        assertEquals(2, evenCount, "Should count 2 even-length strings");
    }

    @Test
    public void testCountOddWithMixedLengths() throws ArrayException {
        ArrayEntity entity = buildEntity("ab", "abc", "abcd", "x"); // lengths: 2, 3, 4, 1 → odd: 3, 1
        int oddCount = service.countOdd(entity);
        assertEquals(2, oddCount, "Should count 2 odd-length strings");
    }

    @Test
    public void testCountEvenWithAllEven() throws ArrayException {
        ArrayEntity entity = buildEntity("aa", "bb", "cccc"); // lengths: 2, 2, 4
        assertAll(
                () -> assertEquals(3, service.countEven(entity)),
                () -> assertEquals(0, service.countOdd(entity))
        );
    }

    @Test
    public void testCountOddWithAllOdd() throws ArrayException {
        ArrayEntity entity = buildEntity("a", "bbb", "eee"); // lengths: 1, 3, 3
        assertAll(
                () -> assertEquals(0, service.countEven(entity)),
                () -> assertEquals(3, service.countOdd(entity))
        );
    }

    @Test
    public void testEmptyStringsAreNotEven() throws ArrayException {
        ArrayEntity entity = buildEntity("", "a", ""); // lengths: 0, 1, 0 → even: 0, 0
        assertAll(
                () -> assertEquals(0, service.countEven(entity)),
                () -> assertEquals(1, service.countOdd(entity))
        );
    }
}

package main.com.innowise.taskarray.service.stream;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.stream.impl.ArrayStringMinMaxStreamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringMinMaxStreamServiceTest {

    private ArrayStringMinMaxStreamService service;

    @BeforeEach
    public void setup() {
        service = new ArrayStringMinMaxStreamService();
    }

    private ArrayEntity buildEntity(String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(1)
                .setData(data)
                .build();
    }

    @Test
    public void testMinAndMaxWithMixedLengths() throws ArrayException {
        ArrayEntity entity = buildEntity("a", "abc", "abcd", "xy"); // lengths: 1, 3, 4, 2
        assertAll(
                () -> assertEquals(1, service.min(entity), "Minimum length should be 1"),
                () ->assertEquals(4, service.max(entity), "Maximum length should be 4")
        );
    }

    @Test
    public void testMinAndMaxWithUniformLengths() throws ArrayException {
        ArrayEntity entity = buildEntity("aa", "bb", "cc"); // lengths: 2, 2, 2
        assertAll(
                () -> assertEquals(2, service.min(entity)),
                () -> assertEquals(2, service.max(entity))
        );
    }

    @Test
    public void testMinAndMaxWithSingleElement() throws ArrayException {
        ArrayEntity entity = buildEntity("hello"); // length: 5
        assertAll(
                () -> assertEquals(5, service.min(entity)),
                () -> assertEquals(5, service.max(entity))
        );
    }

    @Test
    public void testMinAndMaxWithShortAndLongStrings() throws ArrayException {
        ArrayEntity entity = buildEntity("x", "longstringhere", "mid"); // lengths: 1, 14, 3
        assertAll(
                () -> assertEquals(1, service.min(entity)),
                () -> assertEquals(14, service.max(entity))
        );
    }
}

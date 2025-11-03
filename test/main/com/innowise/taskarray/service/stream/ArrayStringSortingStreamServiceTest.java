package main.com.innowise.taskarray.service.stream;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.stream.impl.ArrayStringSortingStreamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringSortingStreamServiceTest {

    private ArrayStringSortingStreamService service;

    @BeforeEach
    public void setup() {
        service = new ArrayStringSortingStreamService();
    }

    private ArrayEntity buildEntity(String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(1)
                .setData(data)
                .build();
    }

    private void assertSortedByLength(String[] result, int... expectedLengths) {
        assertEquals(expectedLengths.length, result.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(expectedLengths[i], result[i].length(), "Mismatch at index " + i);
        }
    }

    @Test
    public void testQuickSort() throws ArrayException {
        ArrayEntity entity = buildEntity("aaa", "b", "cccc", "dd"); // lengths: 3,1,4,2
        String[] sorted = service.quickSort(entity);
        assertSortedByLength(sorted, 1, 2, 3, 4);
    }

    @Test
    public void testShellSort() throws ArrayException {
        ArrayEntity entity = buildEntity("aaa", "b", "cccc", "dd"); // lengths: 3,1,4,2
        String[] sorted = service.shellSort(entity);
        assertSortedByLength(sorted, 1, 2, 3, 4);
    }

    @Test
    public void testBubbleSort() throws ArrayException {
        ArrayEntity entity = buildEntity("aaa", "b", "cccc", "dd"); // lengths: 3,1,4,2
        String[] sorted = service.bubbleSort(entity);
        assertSortedByLength(sorted, 1, 2, 3, 4);
    }

    @Test
    public void testAlreadySortedInput() throws ArrayException {
        ArrayEntity entity = buildEntity("a", "bb", "ccc", "dddd"); // lengths: 1,2,3,4
        assertAll(
                () -> assertSortedByLength(service.quickSort(entity), 1, 2, 3, 4),
                () -> assertSortedByLength(service.shellSort(entity), 1, 2, 3, 4),
                () -> assertSortedByLength(service.bubbleSort(entity), 1, 2, 3, 4)
        );
    }

    @Test
    public void testReverseSortedInput() throws ArrayException {
        ArrayEntity entity = buildEntity("dddd", "ccc", "bb", "a"); // lengths: 4,3,2,1
        assertAll(
                () -> assertSortedByLength(service.quickSort(entity), 1, 2, 3, 4),
                () -> assertSortedByLength(service.shellSort(entity), 1, 2, 3, 4),
                () -> assertSortedByLength(service.bubbleSort(entity), 1, 2, 3, 4)
        );
    }
}

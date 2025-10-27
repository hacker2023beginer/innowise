package test.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.impl.ArrayStringSortingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringSortingServiceTest {

    private final ArrayStringSortingService service = new ArrayStringSortingService();

    private ArrayEntity createEntity(String[] input, int id) throws ArrayException {
        return ArrayEntity.newBuilder().setData(input).setId(id).build();
    }

    private String[] expectedSorted(String[] input) {
        return input.clone(); // sorted manually by length
    }

    @Test
    void testQuickSortBasic() throws ArrayException {
        String[] input = {"aaa", "a", "aaaaa", "aa"}; // lengths: 3,1,5,2 → sorted: a, aa, aaa, aaaaa
        ArrayEntity entity = createEntity(input, 1);
        String[] result = service.quickSort(entity);
        assertArrayEquals(new String[]{"a", "aa", "aaa", "aaaaa"}, result);
    }

    @Test
    void testShellSortBasic() throws ArrayException {
        String[] input = {"aaa", "a", "aaaaa", "aa"};
        ArrayEntity entity = createEntity(input, 2);
        String[] result = service.shellSort(entity);
        assertArrayEquals(new String[]{"a", "aa", "aaa", "aaaaa"}, result);
    }

    @Test
    void testBubbleSortBasic() throws ArrayException {
        String[] input = {"aaa", "a", "aaaaa", "aa"};
        ArrayEntity entity = createEntity(input, 3);
        String[] result = service.bubbleSort(entity);
        assertArrayEquals(new String[]{"a", "aa", "aaa", "aaaaa"}, result);
    }

    @Test
    void testAlreadySortedInput() throws ArrayException {
        String[] input = {"a", "aa", "aaa", "aaaaa"};
        ArrayEntity entity = createEntity(input, 4);
        assertArrayEquals(input, service.quickSort(entity));
        assertArrayEquals(input, service.shellSort(entity));
        assertArrayEquals(input, service.bubbleSort(entity));
    }

    @Test
    void testSingleElement() throws ArrayException {
        String[] input = {"abc"};
        ArrayEntity entity = createEntity(input, 5);
        assertArrayEquals(input, service.quickSort(entity));
        assertArrayEquals(input, service.shellSort(entity));
        assertArrayEquals(input, service.bubbleSort(entity));
    }

    @Test
    void testSortingPreservesOriginalArray() throws ArrayException {
        String[] input = {"aaa", "a", "aaaaa", "aa"};
        ArrayEntity entity = createEntity(input, 6);
        String[] original = entity.getData();
        String[] sorted = service.quickSort(entity);
        assertNotSame(original, sorted);
        assertArrayEquals(new String[]{"aaa", "a", "aaaaa", "aa"}, original); // original remains unchanged
    }
}

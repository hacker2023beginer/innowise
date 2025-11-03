package main.com.innowise.taskarray.comparator;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntityComparatorTest {

    private ArrayEntity buildEntity(int id, String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(id)
                .setData(data)
                .build();
    }

    @Test
    public void testSortById() throws ArrayException {
        ArrayEntity e1 = buildEntity(2, "a", "b");
        ArrayEntity e2 = buildEntity(1, "x", "y");
        ArrayEntity e3 = buildEntity(3, "m");

        List<ArrayEntity> list = List.of(e1, e2, e3);
        List<ArrayEntity> sorted = list.stream()
                .sorted(ArrayEntityComparator.ID.getComparator())
                .toList();

        assertAll(
                () -> assertEquals(1, sorted.get(0).getId()),
                () -> assertEquals(2, sorted.get(1).getId()),
                () -> assertEquals(3, sorted.get(2).getId())
        );
    }

    @Test
    public void testSortByDataLength() throws ArrayException {
        ArrayEntity e1 = buildEntity(1, "a", "b", "c");
        ArrayEntity e2 = buildEntity(2, "x");
        ArrayEntity e3 = buildEntity(3, "m", "n");

        List<ArrayEntity> list = List.of(e1, e2, e3);
        List<ArrayEntity> sorted = list.stream()
                .sorted(ArrayEntityComparator.DATA.getComparator())
                .toList();
        assertAll(
                () -> assertEquals(2, sorted.get(0).getId()), // length 1
                () -> assertEquals(3, sorted.get(1).getId()), // length 2
                () -> assertEquals(1, sorted.get(2).getId())  // length 3
        );
    }
}

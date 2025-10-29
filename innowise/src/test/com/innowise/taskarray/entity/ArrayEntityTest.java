package test.com.innowise.taskarray.entity;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntityTest {

    @Test
    void testBuilderCreatesValidEntity() throws ArrayException {
        String[] input = {"abc123", "def456", "GHI789"};
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(1)
                .build();

        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertArrayEquals(input, entity.getData());
    }

    @Test
    void testBuilderFiltersInvalidTokens() throws ArrayException {
        String[] input = {"abc123", "!@#", "def456"};
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(2)
                .build();

        assertNotNull(entity);
        assertEquals(2, entity.getId());
        assertArrayEquals(new String[]{"abc123", "def456"}, entity.getData());
    }

    @Test
    void testBuilderThrowsExceptionOnEmptyInput() {
        String[] input = {};
        assertThrows(ArrayException.class, () ->
                ArrayEntity.newBuilder()
                        .setData(input)
                        .setId(3)
                        .build()
        );
    }

    @Test
    void testBuilderThrowsExceptionOnAllInvalidTokens() {
        String[] input = {"!!!", "###", null};
        assertThrows(ArrayException.class, () ->
                ArrayEntity.newBuilder()
                        .setData(input)
                        .setId(4)
                        .build()
        );
    }

    @Test
    void testEqualsAndHashCode() throws ArrayException {
        String[] input = {"abc", "123"};
        ArrayEntity entity1 = ArrayEntity.newBuilder().setData(input).setId(5).build();
        ArrayEntity entity2 = ArrayEntity.newBuilder().setData(input).setId(5).build();

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testNotEqualsDifferentId() throws ArrayException {
        String[] input = {"abc", "123"};
        ArrayEntity entity1 = ArrayEntity.newBuilder().setData(input).setId(5).build();
        ArrayEntity entity2 = ArrayEntity.newBuilder().setData(input).setId(6).build();

        assertNotEquals(entity1, entity2);
    }

    @Test
    void testNotEqualsDifferentData() throws ArrayException {
        ArrayEntity entity1 = ArrayEntity.newBuilder().setData(new String[]{"abc"}).setId(1).build();
        ArrayEntity entity2 = ArrayEntity.newBuilder().setData(new String[]{"xyz"}).setId(1).build();

        assertNotEquals(entity1, entity2);
    }
}


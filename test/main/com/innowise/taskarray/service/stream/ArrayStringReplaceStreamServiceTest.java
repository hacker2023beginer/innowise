package main.com.innowise.taskarray.service.stream;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.stream.impl.ArrayStringReplaceStreamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.IntPredicate;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringReplaceStreamServiceTest {

    private ArrayStringReplaceStreamService service;

    @BeforeEach
    public void setup() {
        service = new ArrayStringReplaceStreamService();
    }

    private ArrayEntity buildEntity(String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(1)
                .setData(data)
                .build();
    }

    @Test
    public void testReplaceEvenLengthTokens() throws ArrayException {
        ArrayEntity entity = buildEntity("ab", "abc", "abcd", "x"); // lengths: 2, 3, 4, 1
        IntPredicate evenLength = len -> len % 2 == 0;
        String[] result = service.replaceIf(entity, evenLength, "REPLACED");

        assertArrayEquals(new String[]{"REPLACED", "abc", "REPLACED", "x"}, result);
    }

    @Test
    public void testReplaceOddLengthTokens() throws ArrayException {
        ArrayEntity entity = buildEntity("a", "bb", "ccc", "dddd"); // lengths: 1, 2, 3, 4
        IntPredicate oddLength = len -> len % 2 != 0;
        String[] result = service.replaceIf(entity, oddLength, "ODD");

        assertArrayEquals(new String[]{"ODD", "bb", "ODD", "dddd"}, result);
    }

    @Test
    public void testReplaceLengthGreaterThanThree() throws ArrayException {
        ArrayEntity entity = buildEntity("a", "bb", "ccc", "dddd", "eeeee"); // lengths: 1–5
        IntPredicate greaterThanThree = len -> len > 3;
        String[] result = service.replaceIf(entity, greaterThanThree, "LONG");

        assertArrayEquals(new String[]{"a", "bb", "ccc", "LONG", "LONG"}, result);
    }

    @Test
    public void testNoReplacementWhenPredicateAlwaysFalse() throws ArrayException {
        ArrayEntity entity = buildEntity("one", "two", "three");
        IntPredicate neverReplace = len -> false;
        String[] result = service.replaceIf(entity, neverReplace, "X");

        assertArrayEquals(new String[]{"one", "two", "three"}, result);
    }

    @Test
    public void testFullReplacementWhenPredicateAlwaysTrue() throws ArrayException {
        ArrayEntity entity = buildEntity("a", "b", "c");
        IntPredicate alwaysReplace = len -> true;
        String[] result = service.replaceIf(entity, alwaysReplace, "ALL");

        assertArrayEquals(new String[]{"ALL", "ALL", "ALL"}, result);
    }
}

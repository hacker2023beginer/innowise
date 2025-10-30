package test.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.impl.ArrayStringReplaceService;
import org.junit.jupiter.api.Test;

import java.util.function.IntPredicate;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringReplaceServiceTest {

    private final ArrayStringReplaceService service = new ArrayStringReplaceService();

    @Test
    public void testReplaceEvenLengthTokens() throws ArrayException {
        String[] input = {"ab", "abc", "abcd", "xyz"}; // lengths: 2, 3, 4, 3 → even: ab, abcd
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(1)
                .build();

        IntPredicate isEven = len -> len % 2 == 0;
        String replacement = "REPLACED";

        String[] result = service.replaceIf(entity, isEven, replacement);

        assertAll(
                () -> assertArrayEquals(new String[]{"REPLACED", "abc", "REPLACED", "xyz"}, result),
                () -> assertArrayEquals(input, entity.getData()) // ensure original is unchanged
        );
    }

    @Test
    public void testReplaceOddLengthTokens() throws ArrayException {
        String[] input = {"a", "bb", "ccc", "dddd"}; // odd: a, ccc
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(2)
                .build();

        IntPredicate isOdd = len -> len % 2 == 1;
        String replacement = "ODD";

        String[] result = service.replaceIf(entity, isOdd, replacement);

        assertArrayEquals(new String[]{"ODD", "bb", "ODD", "dddd"}, result);
    }

    @Test
    public void testNoReplacementWhenPredicateFails() throws ArrayException {
        String[] input = {"abc", "def", "ghi"};
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(3)
                .build();

        IntPredicate alwaysFalse = len -> false;
        String replacement = "X";

        String[] result = service.replaceIf(entity, alwaysFalse, replacement);

        assertArrayEquals(input, result);
    }

    @Test
    public void testReplaceAllWhenPredicateAlwaysTrue() throws ArrayException {
        String[] input = {"abc", "def", "ghi"};
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(4)
                .build();

        IntPredicate alwaysTrue = len -> true;
        String replacement = "ALL";

        String[] result = service.replaceIf(entity, alwaysTrue, replacement);

        assertArrayEquals(new String[]{"ALL", "ALL", "ALL"}, result);
    }

    @Test
    public void testReplacePreservesOriginalArray() throws ArrayException {
        String[] input = {"abc", "def"};
        ArrayEntity entity = ArrayEntity.newBuilder()
                .setData(input)
                .setId(5)
                .build();

        String[] result = service.replaceIf(entity, len -> len == 3, "X");

        assertAll(
                () -> assertNotSame(result, entity.getData()), // ensure clone was used
                () -> assertArrayEquals(input, entity.getData()) // original remains unchanged
        );
    }
}

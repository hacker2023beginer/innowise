package test.com.innowise.taskarray.parser;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.parser.impl.ArrayEntityParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntityParserTest {

    private final ArrayEntityParser parser = new ArrayEntityParser();

    // --- parseStringDataToArrayEntityData() ---

    @Test
    void testParseValidTokens() throws ArrayException {
        String[] input = {"abc123", "DEF456", "789"};
        String[] result = parser.parseStringDataToArrayEntityData(input);
        assertArrayEquals(input, result);
    }

    @Test
    void testParseMixedTokens() throws ArrayException {
        String[] input = {"abc123", "!@#", "DEF456", null, "   "};
        String[] result = parser.parseStringDataToArrayEntityData(input);
        assertArrayEquals(new String[]{"abc123", "DEF456"}, result);
    }

    @Test
    void testParseAllInvalidTokensThrowsException() {
        String[] input = {"!!!", "", null, "   "};
        assertThrows(ArrayException.class, () -> parser.parseStringDataToArrayEntityData(input));
    }

    // --- parseStringListToArrayEntityList() ---

    @Test
    void testParseValidLinesToEntities() throws ArrayException {
        List<String> input = List.of("abc123 def456", "ghi789");
        List<String[]> parameter = parser.parseStringListToStringArrayList(input);
        List<ArrayEntity> result = parser.parseStringListToArrayEntityList(parameter);

        assertEquals(2, result.size());
        assertEquals(0, result.get(0).getId());
        assertEquals(1, result.get(1).getId());
        assertArrayEquals(new String[]{"abc123", "def456"}, result.get(0).getData());
        assertArrayEquals(new String[]{"ghi789"}, result.get(1).getData());
    }

    @Test
    void testParseLinesWithInvalidTokens() throws ArrayException {
        List<String> input = List.of("abc123 !@# def456", "###", "ghi789");
        List<String[]> parameter = parser.parseStringListToStringArrayList(input);
        List<ArrayEntity> result = parser.parseStringListToArrayEntityList(parameter);

        assertEquals(2, result.size());
        assertArrayEquals(new String[]{"abc123", "def456"}, result.get(0).getData());
        assertArrayEquals(new String[]{"ghi789"}, result.get(1).getData());
    }

    @Test
    void testParseEmptyListThrowsException() throws ArrayException{
        List<String> input = List.of();
        List<String[]> parameter = parser.parseStringListToStringArrayList(input);
        assertThrows(ArrayException.class, () -> parser.parseStringListToArrayEntityList(parameter));
    }

    @Test
    void testParseNullListThrowsException() {
        assertThrows(ArrayException.class, () -> parser.parseStringListToArrayEntityList(null));
    }

    @Test
    void testParseListWithOnlyInvalidLinesReturnsEmpty() throws ArrayException {
        List<String> input = Arrays.asList("!!! ###", "   ", "");
        List<String[]> parameter = parser.parseStringListToStringArrayList(input);
        List<ArrayEntity> result = parser.parseStringListToArrayEntityList(parameter);
        assertTrue(result.isEmpty());
    }
}

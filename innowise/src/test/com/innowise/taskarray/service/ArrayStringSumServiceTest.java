package test.com.innowise.taskarray.service;

import main.com.innowise.taskarray.service.impl.ArrayStringSumService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringSumServiceTest {

    private final ArrayStringSumService service = new ArrayStringSumService();

    @Test
    public void testSumWithRegularStrings() {
        String s1 = "abc";   // length = 3
        String s2 = "defg";  // length = 4
        int result = service.sum(s1, s2);
        assertEquals(7, result);
    }

    @Test
    public void testSumWithEmptyStrings() {
        String s1 = "";
        String s2 = "";
        int result = service.sum(s1, s2);
        assertEquals(0, result);
    }

    @Test
    public void testSumWithOneEmptyString() {
        String s1 = "abc";
        String s2 = "";
        int result = service.sum(s1, s2);
        assertEquals(3, result);
    }

    @Test
    public void testSumWithWhitespaceStrings() {
        String s1 = " ";
        String s2 = "   ";
        int result = service.sum(s1, s2);
        assertEquals(4, result);
    }

    @Test
    public void testSumWithUnicodeCharacters() {
        String s1 = "тест";   // 4 characters
        String s2 = "123";    // 3 characters
        int result = service.sum(s1, s2);
        assertEquals(7, result);
    }

    @Test
    public void testSumWithNullThrowsException() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> service.sum(null, "abc")),
                () -> assertThrows(NullPointerException.class, () -> service.sum("abc", null)),
                () -> assertThrows(NullPointerException.class, () -> service.sum(null, null))
        );
    }
}

package main.com.innowise.taskarray.service.stream;

import main.com.innowise.taskarray.service.stream.impl.ArrayStringSumStreamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringSumStreamServiceTest {

    private ArrayStringSumStreamService service;

    @BeforeEach
    public void setup() {
        service = new ArrayStringSumStreamService();
    }

    @Test
    public void testSumWithTwoValidStrings() {
        int result = service.sum("hello", "world"); // lengths: 5 + 5
        assertEquals(10, result);
    }

    @Test
    public void testSumWithOneNull() {
        int result = service.sum(null, "abc"); // only "abc" → length 3
        assertEquals(3, result);
    }

    @Test
    public void testSumWithBothNull() {
        int result = service.sum(null, null);
        assertEquals(0, result);
    }

    @Test
    public void testSumWithEmptyStrings() {
        int result = service.sum("", ""); // both empty → length 0 + 0
        assertEquals(0, result);
    }

    @Test
    public void testSumWithMixedEmptyAndValid() {
        int result = service.sum("", "abcde"); // length 0 + 5
        assertEquals(5, result);
    }

    @Test
    public void testSumWithWhitespaceStrings() {
        int result = service.sum(" ", "   "); // length 1 + 3
        assertEquals(4, result);
    }
}

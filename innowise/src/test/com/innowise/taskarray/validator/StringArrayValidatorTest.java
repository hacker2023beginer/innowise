package test.com.innowise.taskarray.validator;

import main.com.innowise.taskarray.validator.impl.StringArrayValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringArrayValidatorTest {

    private final StringArrayValidator validator = new StringArrayValidator();

    // --- isValidLine() ---

    @Test
    void testValidLine() {
        assertTrue(validator.isValidLine("abc def"));
    }

    @Test
    void testLineWithSpacesOnly() {
        assertFalse(validator.isValidLine("     "));
    }

    @Test
    void testEmptyLine() {
        assertFalse(validator.isValidLine(""));
    }

    @Test
    void testNullLine() {
        assertFalse(validator.isValidLine(null));
    }

    // --- isValidToken() ---

    @Test
    void testValidTokenAlphanumeric() {
        assertTrue(validator.isValidToken("abc123"));
    }

    @Test
    void testValidTokenUppercase() {
        assertTrue(validator.isValidToken("ABCDEF"));
    }

    @Test
    void testValidTokenDigitsOnly() {
        assertTrue(validator.isValidToken("123456"));
    }

    @Test
    void testInvalidTokenWithSymbols() {
        assertFalse(validator.isValidToken("abc$123"));
        assertFalse(validator.isValidToken("hello!"));
        assertFalse(validator.isValidToken("###"));
    }

    @Test
    void testInvalidTokenWithSpaces() {
        assertFalse(validator.isValidToken("abc def"));
    }

    @Test
    void testInvalidTokenWithUnicode() {
        assertFalse(validator.isValidToken("тест123"));
    }

    @Test
    void testEmptyToken() {
        assertFalse(validator.isValidToken(""));
    }

    @Test
    void testNullToken() {
        assertFalse(validator.isValidToken(null));
    }
}

package test.com.innowise.taskarray.validator;

import main.com.innowise.taskarray.validator.impl.StringArrayValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringArrayValidatorTest {

    private final StringArrayValidator validator = new StringArrayValidator();

    // --- isValidLine() ---

    @Test
    public void testValidLine() {
        assertTrue(validator.isValidLine("abc def"));
    }

    @Test
    public void testLineWithSpacesOnly() {
        assertFalse(validator.isValidLine("     "));
    }

    @Test
    public void testEmptyLine() {
        assertFalse(validator.isValidLine(""));
    }

    @Test
    public void testNullLine() {
        assertFalse(validator.isValidLine(null));
    }

    // --- isValidToken() ---

    @Test
    public void testValidTokenAlphanumeric() {
        assertTrue(validator.isValidToken("abc123"));
    }

    @Test
    public void testValidTokenUppercase() {
        assertTrue(validator.isValidToken("ABCDEF"));
    }

    @Test
    public void testValidTokenDigitsOnly() {
        assertTrue(validator.isValidToken("123456"));
    }

    @Test
    public void testInvalidTokenWithSymbols() {
        assertAll(
                () -> assertFalse(validator.isValidToken("abc$123")),
                () -> assertFalse(validator.isValidToken("hello!")),
                () -> assertFalse(validator.isValidToken("###"))
        );
    }

    @Test
    public void testInvalidTokenWithSpaces() {
        assertFalse(validator.isValidToken("abc def"));
    }

    @Test
    public void testInvalidTokenWithUnicode() {
        assertFalse(validator.isValidToken("тест123"));
    }

    @Test
    public void testEmptyToken() {
        assertFalse(validator.isValidToken(""));
    }

    @Test
    public void testNullToken() {
        assertFalse(validator.isValidToken(null));
    }
}

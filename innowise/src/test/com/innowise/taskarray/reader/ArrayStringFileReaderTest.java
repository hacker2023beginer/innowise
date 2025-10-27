package test.com.innowise.taskarray.reader;

import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.reader.impl.ArrayStringFileReader;
import main.com.innowise.taskarray.validator.impl.StringArrayValidator;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringFileReaderTest {

    private final ArrayStringFileReader reader = new ArrayStringFileReader(new StringArrayValidator());

    @Test
    void testReadFromInputFile() throws ArrayException {
        Path filePath = Paths.get("innowise", "data/input.txt");

        List<String> result = reader.readFromFile(filePath);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        assertTrue(result.contains("abc123 def456 GHI789"));
        assertTrue(result.contains("validOne validTwo validThree"));
        assertTrue(result.contains("nulltoken  validToken anotherValid123"));

        assertTrue(result.contains("%%% ??? ***"));
        assertTrue(result.contains("!@# invalid$$$ 123abc"));
        assertFalse(result.contains("   spaces   between   tokens"));
    }
}

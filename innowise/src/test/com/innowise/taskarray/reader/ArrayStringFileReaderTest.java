package test.com.innowise.taskarray.reader;

import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.reader.impl.ArrayStringFileReader;
import main.com.innowise.taskarray.validator.impl.StringArrayValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringFileReaderTest {

    private final ArrayStringFileReader reader = new ArrayStringFileReader(new StringArrayValidator());

    @Test
    void testReadValidLines(@TempDir Path tempDir) throws IOException, ArrayException {
        Path file = tempDir.resolve("valid.txt");
        Files.write(file, List.of(
                "abc 123",     // valid
                "   def456   ",// valid with trim
                "",            // blank
                "   ",         // blank with spaces
                "ghi789"       // valid
        ));

        List<String> result = reader.readFromFile(file);
        assertEquals(3, result.size());
        assertEquals("abc 123", result.get(0));
        assertEquals("def456", result.get(1));
        assertEquals("ghi789", result.get(2));
    }

    @Test
    void testReadEmptyFile(@TempDir Path tempDir) throws IOException, ArrayException {
        Path file = tempDir.resolve("empty.txt");
        Files.write(file, List.of());

        List<String> result = reader.readFromFile(file);
        assertTrue(result.isEmpty());
    }

    @Test
    void testReadFileWithOnlyBlankLines(@TempDir Path tempDir) throws IOException, ArrayException {
        Path file = tempDir.resolve("blank.txt");
        Files.write(file, List.of("   ", "", "\t", "\n"));

        List<String> result = reader.readFromFile(file);
        assertTrue(result.isEmpty());
    }

    @Test
    void testIOExceptionThrowsArrayException() {
        Path invalidPath = Path.of("nonexistent/file.txt");
        assertThrows(ArrayException.class, () -> reader.readFromFile(invalidPath));
    }
}

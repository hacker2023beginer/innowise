package main.com.innowise.taskarray.reader.impl;

import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.reader.StringFileReader;
import main.com.innowise.taskarray.validator.ArrayValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayStringFileReader implements StringFileReader {

    private static final Logger logger = Logger.getLogger(ArrayStringFileReader.class.getName());

    private final ArrayValidator validator;

    public ArrayStringFileReader(ArrayValidator validator) {
        this.validator = validator;
        logger.fine("ArrayStringFileReader initialized with validator: " + validator.getClass().getSimpleName());
    }

    @Override
    public List<String> readFromFile(Path path) throws ArrayException {
        if (path == null) {
            logger.warning("Path is null");
            throw new ArrayException("Path must not be null");
        }
        logger.info("readFromFile() called with path: " + path);
        List<String> listOfStrings;
        try {
            Stream<String> fileStream = Files.readAllLines(path).stream();
            listOfStrings = fileStream
                    .map(String::trim)
                    .peek(line -> logger.fine("Read line: " + line))
                    .filter(line -> !line.isBlank())
                    .collect(Collectors.toList());
            logger.info("Successfully read " + listOfStrings.size() + " non-blank lines from file");
        } catch (IOException e) {
            logger.severe("IOException while reading file: " + e.getMessage());
            throw new ArrayException("Error while reading file", e);
        }
        return listOfStrings;
    }
}

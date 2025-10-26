package main.com.innowise.taskarray.reader.impl;

import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.reader.StringFileReader;
import main.com.innowise.taskarray.validator.ArrayValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayStringFileReader implements StringFileReader {

    private final ArrayValidator validator;

    public ArrayStringFileReader(ArrayValidator validator) {
        this.validator = validator;
    }

    public List<String> readFromFile(Path path) throws ArrayException {
        List<String> listOfStrings;
        try {
            Stream<String> fileStream = Files.readAllLines(path).stream();
            listOfStrings = fileStream.map(String::trim)
                    .filter(line -> !line.isBlank())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ArrayException("Error while reading file", e);
        }
        return listOfStrings;
    }
}


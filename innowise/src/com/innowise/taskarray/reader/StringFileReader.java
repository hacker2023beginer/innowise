package com.innowise.taskarray.reader;

import com.innowise.taskarray.exception.ArrayException;

import java.nio.file.Path;
import java.util.List;

public interface StringFileReader {
    List<String> readFromFile(Path path) throws ArrayException;
}

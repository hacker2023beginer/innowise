package main.com.innowise.taskarray.parser;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;

import java.util.List;

public interface EntityParser {
    List<ArrayEntity> parseStringListToEntityArray(List<String> stringList, int id) throws ArrayException;
}

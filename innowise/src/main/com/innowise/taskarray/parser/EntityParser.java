package main.com.innowise.taskarray.parser;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;

import java.util.List;

public interface EntityParser {
    List<ArrayEntity> parseStringListToArrayEntityList(List<String> stringList) throws ArrayException;
    String[] parseStringDataToArrayEntityData(String[] data) throws ArrayException;
}

package main.com.innowise.taskarray.parser;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;

import java.util.List;

public interface EntityParser {
    List<String[]> parseStringListToStringArrayList(List<String> stringList) throws ArrayException;
    List<ArrayEntity> parseStringListToArrayEntityList(List<String[]> stringArrayList) throws ArrayException;
    String[] parseStringDataToArrayEntityData(String[] data) throws ArrayException;
}

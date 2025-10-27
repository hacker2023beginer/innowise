package main.com.innowise.taskarray.parser.impl;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.parser.EntityParser;
import main.com.innowise.taskarray.validator.ArrayValidator;
import main.com.innowise.taskarray.validator.impl.StringArrayValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ArrayEntityParser implements EntityParser {
    private static final String SPACE_DELIMITER_REGEX = "\\s+";

    @Override
    public List<ArrayEntity> parseStringListToArrayEntityList(List<String> stringList, int id) throws ArrayException {
        List<ArrayEntity> arrayEntityList;
        AtomicInteger idCounter = new AtomicInteger(id);
        if (stringList == null || stringList.isEmpty()) {
            throw new ArrayException();
        }
        ArrayValidator arrayValidator = new StringArrayValidator();
        Stream<String> stream = stringList.stream();
        arrayEntityList = stream
                .map(String::trim)
                .filter(arrayValidator::isValidLine)
                .map(line -> line.split(SPACE_DELIMITER_REGEX))
                .map(tokens -> Arrays.stream(tokens)
                        .filter(arrayValidator::isValidToken)
                        .toArray(String[]::new))
                .map(tokens -> {
                    try {
                        return ArrayEntity.newBuilder()
                                .setData(tokens)
                                .setId(idCounter.getAndIncrement())
                                .build();
                    } catch (ArrayException e) {
                        return null;
                    }
                })
                .filter(obj -> obj != null)
                .toList();
        return arrayEntityList;
    }

    @Override
    public String[] parseStringDataToArrayEntityData(String[] data) throws ArrayException {
        ArrayValidator stringArrayValidator = new StringArrayValidator();
        int lengthEntityData = 0;
        for (int i = 0; i < data.length; i++) {
            if (stringArrayValidator.isValidToken(data[i])){
                lengthEntityData++;
            }
        }
        if (lengthEntityData == 0) {
            throw new ArrayException("Array contains invalid tokens");
        }
        String[] correctData = new String[lengthEntityData];
        int indexCorrectData = 0;
        for (int i = 0; i < data.length; i++) {
            if (stringArrayValidator.isValidToken(data[i])){
                correctData[indexCorrectData] = data[i];
                indexCorrectData++;
            }
        }
        return correctData;
    }
}

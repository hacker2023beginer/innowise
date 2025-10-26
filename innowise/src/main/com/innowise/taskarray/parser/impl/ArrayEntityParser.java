package main.com.innowise.taskarray.parser.impl;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.parser.EntityParser;
import main.com.innowise.taskarray.validator.ArrayValidator;
import main.com.innowise.taskarray.validator.impl.StringArrayValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ArrayEntityParser implements EntityParser {
    private static final String SPACE_DELIMITER_REGEX = "\\s+";

    @Override
    public List<ArrayEntity> parseStringListToEntityArray(List<String> stringList, int id) throws ArrayException {
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
                .filter(arrayValidator::isValidTokens)
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
}

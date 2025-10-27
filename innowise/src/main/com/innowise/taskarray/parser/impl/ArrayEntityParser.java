package main.com.innowise.taskarray.parser.impl;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.parser.EntityParser;
import main.com.innowise.taskarray.validator.ArrayValidator;
import main.com.innowise.taskarray.validator.impl.StringArrayValidator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ArrayEntityParser implements EntityParser {

    private static final Logger logger = Logger.getLogger(ArrayEntityParser.class.getName());
    private static final String SPACE_DELIMITER_REGEX = "\\s+";

    @Override
    public List<ArrayEntity> parseStringListToArrayEntityList(List<String> stringList, int id) throws ArrayException {
        logger.info("parseStringListToArrayEntityList() called with id=" + id);
        if (stringList == null || stringList.isEmpty()) {
            logger.warning("Input string list is null or empty");
            throw new ArrayException("Input list must not be null or empty");
        }

        ArrayValidator arrayValidator = new StringArrayValidator();
        AtomicInteger idCounter = new AtomicInteger(id);

        List<ArrayEntity> arrayEntityList = stringList.stream()
                .filter(arrayValidator::isValidLine)
                .map(String::trim)
                .peek(line -> logger.fine("Trimmed line: " + line))
                .filter(arrayValidator::isValidLine)
                .peek(validLine -> logger.fine("Valid line: " + validLine))
                .map(line -> line.split(SPACE_DELIMITER_REGEX))
                .map(tokens -> {
                    String[] filtered = Arrays.stream(tokens)
                            .filter(arrayValidator::isValidToken)
                            .toArray(String[]::new);
                    logger.fine("Filtered tokens: " + Arrays.toString(filtered));
                    return filtered;
                })
                .filter(filtered -> filtered.length > 0)
                .map(tokens -> {
                    try {
                        ArrayEntity entity = ArrayEntity.newBuilder()
                                .setData(tokens)
                                .setId(idCounter.getAndIncrement())
                                .build();
                        logger.fine("Built ArrayEntity with id=" + entity.getId());
                        return entity;
                    } catch (ArrayException e) {
                        logger.warning("Failed to build ArrayEntity: " + e.getMessage());
                        return null;
                    }
                })
                .filter(obj -> obj != null)
                .toList();

        logger.info("parseStringListToArrayEntityList() completed with " + arrayEntityList.size() + " entities");
        return arrayEntityList;
    }

    @Override
    public String[] parseStringDataToArrayEntityData(String[] data) throws ArrayException {
        logger.info("parseStringDataToArrayEntityData() called with data: " + Arrays.toString(data));
        ArrayValidator stringArrayValidator = new StringArrayValidator();

        int lengthEntityData = 0;
        for (String token : data) {
            if (stringArrayValidator.isValidToken(token)) {
                lengthEntityData++;
            } else {
                logger.fine("Invalid token skipped: " + token);
            }
        }

        if (lengthEntityData == 0) {
            logger.warning("No valid tokens found in input");
            throw new ArrayException("Array contains invalid tokens");
        }

        String[] correctData = new String[lengthEntityData];
        int index = 0;
        for (String token : data) {
            if (stringArrayValidator.isValidToken(token)) {
                correctData[index++] = token;
            }
        }

        logger.fine("Filtered valid tokens: " + Arrays.toString(correctData));
        logger.info("parseStringDataToArrayEntityData() completed");
        return correctData;
    }
}

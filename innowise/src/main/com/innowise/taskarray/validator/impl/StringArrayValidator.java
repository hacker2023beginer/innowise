package main.com.innowise.taskarray.validator.impl;

import main.com.innowise.taskarray.validator.ArrayValidator;

import java.util.logging.Logger;

public class StringArrayValidator implements ArrayValidator {

    private static final Logger logger = Logger.getLogger(StringArrayValidator.class.getName());
    private static final String TOKEN_REGEX = "^[a-zA-Z0-9]+$";

    @Override
    public boolean isValidLine(String line) {
        logger.fine("isValidLine() called with: \"" + line + "\"");
        boolean result = line != null && !line.isBlank();
        logger.fine("isValidLine() result: " + result);
        return result;
    }

    @Override
    public boolean isValidToken(String token) {
        logger.fine("isValidToken() called with: \"" + token + "\"");
        boolean result = token != null && token.trim().matches(TOKEN_REGEX);
        logger.fine("isValidToken() result: " + result);
        return result;
    }
}

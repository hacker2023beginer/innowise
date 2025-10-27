package main.com.innowise.taskarray.validator.impl;

import main.com.innowise.taskarray.validator.ArrayValidator;

public class StringArrayValidator implements ArrayValidator {
    private static final String TOKEN_REGEX = "^[a-zA-Z0-9]+$";

    @Override
    public boolean isValidLine(String line) {
        return line != null && !line.isBlank();
    }

    public boolean isValidToken(String token) {
        return token != null && token.trim().matches(TOKEN_REGEX);
    }

}

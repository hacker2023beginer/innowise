package main.com.innowise.taskarray.validator.impl;

import main.com.innowise.taskarray.validator.ArrayValidator;

public class StringArrayValidator implements ArrayValidator {
    private static final String TOKEN_REGEX = "^[a-zA-Z0-9]+$";

    @Override
    public boolean isValidLine(String line) {
        return line != null && !line.isBlank();
    }

    @Override
    public boolean isValidTokens(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return false;
        }

        for (String token : tokens) {
            if (token == null || !token.trim().matches(TOKEN_REGEX)) {
                return false;
            }
        }

        return true;
    }
}

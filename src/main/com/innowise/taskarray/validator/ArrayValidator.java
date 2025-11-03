package main.com.innowise.taskarray.validator;

public interface ArrayValidator {
    String TOKEN_REGEX = "^[a-zA-Z0-9]+$";
    boolean isValidLine(String line);
    boolean isValidToken(String token);
}

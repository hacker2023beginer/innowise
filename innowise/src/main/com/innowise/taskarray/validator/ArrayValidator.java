package main.com.innowise.taskarray.validator;

public interface ArrayValidator {
    boolean isValidLine(String line);
    boolean isValidTokens(String[] tokens);
}

package main.com.innowise.taskarray.util;

public class ArrayEntityIdGenerator {
    private static int id = 0;
    public static int nextId() {
        return id++;
    }
}

package main.com.innowise.taskarray.entity;

import java.util.Arrays;

public class ArrayParameters {
    private final int sum;
    private final double average;
    private final int max;
    private final int min;

    public ArrayParameters(String[] data) {
        this.sum = Arrays.stream(data).mapToInt(String::length).sum();
        this.average = data.length > 0 ? (double) sum / data.length : 0;
        this.max = Arrays.stream(data).mapToInt(String::length).max().orElse(Integer.MIN_VALUE);
        this.min = Arrays.stream(data).mapToInt(String::length).min().orElse(Integer.MAX_VALUE);
    }
}


package main.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;

public interface AverageService {
    double average(ArrayEntity array) throws ArrayException;
}

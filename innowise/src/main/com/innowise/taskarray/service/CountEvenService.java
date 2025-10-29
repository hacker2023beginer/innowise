package main.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.ArrayEntity;

public interface CountEvenService {
    int countEven(ArrayEntity array);
    int countOdd(ArrayEntity array);
}

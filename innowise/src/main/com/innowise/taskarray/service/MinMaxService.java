package main.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;

public interface MinMaxService {
    int min(ArrayEntity array);
    int max(ArrayEntity array);
}

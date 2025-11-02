package main.com.innowise.taskarray.repository;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;

import java.util.List;

public interface ArrayRepository {
    boolean add(ArrayEntity entity);

    boolean remove(ArrayEntity entity);

    List<ArrayEntity> findById(int id);

    List<ArrayEntity> findByArrayLengthGreaterThan(int threshold);

    List<ArrayEntity> sortById() throws ArrayException;

    ArrayEntity remove(int id);

    int indexOf(ArrayEntity entity);

    boolean addAll(List<ArrayEntity> entities);

    List<ArrayEntity> sortByDataLength() throws ArrayException;

    List<ArrayEntity> sortByFirstDataElementLength() throws ArrayException;

    List<ArrayEntity> query(EntitySpecification specification);
}

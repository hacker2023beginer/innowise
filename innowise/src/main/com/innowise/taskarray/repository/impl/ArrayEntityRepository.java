package main.com.innowise.taskarray.repository.impl;


import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.repository.ArrayRepository;
import main.com.innowise.taskarray.repository.EntitySpecification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayEntityRepository implements ArrayRepository {
    private final List<ArrayEntity> arrayEntityList = new ArrayList<>();

    @Override
    public boolean add(ArrayEntity entity) {
        return arrayEntityList.add(entity);
    }

    @Override
    public boolean addAll(List<ArrayEntity> entities) {
        return arrayEntityList.addAll(entities);
    }

    @Override
    public int indexOf(ArrayEntity entity) {
        return arrayEntityList.indexOf(entity);
    }

    @Override
    public boolean remove(ArrayEntity entity) {
        return arrayEntityList.remove(entity);
    }

    @Override
    public ArrayEntity remove(int index) {
        return arrayEntityList.remove(index);
    }

    @Override
    public List<ArrayEntity> findById(int id) {
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        return arrayEntityListCopy.stream().filter(entity -> entity.getId() == id).toList();
    }

    @Override
    public List<ArrayEntity> findByArrayLengthGreaterThan(int threshold) {
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        return arrayEntityListCopy.stream().filter(entity -> entity.getData().length > threshold).toList();
    }

    @Override
    public List<ArrayEntity> sortById() throws ArrayException {
        Comparator<ArrayEntity> comparator = ArrayEntity.getComparatorBy("id");
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        arrayEntityListCopy.sort(comparator);
        return arrayEntityListCopy;
    }

    @Override
    public List<ArrayEntity> sortByDataLength() throws ArrayException {
        Comparator<ArrayEntity> comparator = ArrayEntity.getComparatorBy("dataLength");
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        arrayEntityListCopy.sort(comparator);
        return arrayEntityListCopy;
    }

    @Override
    public List<ArrayEntity> sortByFirstDataElementLength() throws ArrayException {
        Comparator<ArrayEntity> comparator = ArrayEntity.getComparatorBy("firstElemLength");
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        arrayEntityListCopy.sort(comparator);
        return arrayEntityListCopy;
    }

    @Override
    public List<ArrayEntity> query(EntitySpecification specification) {
        return arrayEntityList.stream()
                .filter(specification::specify)
                .toList();
    }
}

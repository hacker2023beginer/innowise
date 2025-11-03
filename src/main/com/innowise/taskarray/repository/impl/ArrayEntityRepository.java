package main.com.innowise.taskarray.repository.impl;


import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.repository.EntitySpecification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayEntityRepository {

    private static ArrayEntityRepository instance;
    private final List<ArrayEntity> arrayEntityList = new ArrayList<>();

    private ArrayEntityRepository() {
    }

    public static ArrayEntityRepository getInstance() {
        if (instance == null) {
            instance = new ArrayEntityRepository();
        }
        return instance;
    }

    public boolean add(ArrayEntity entity) {
        return arrayEntityList.add(entity);
    }


    public boolean addAll(List<ArrayEntity> entities) {
        return arrayEntityList.addAll(entities);
    }

    public int indexOf(ArrayEntity entity) {
        return arrayEntityList.indexOf(entity);
    }

    public boolean remove(ArrayEntity entity) {
        return arrayEntityList.remove(entity);
    }

    public ArrayEntity remove(int index) {
        return arrayEntityList.remove(index);
    }

    public List<ArrayEntity> findById(int id) {
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        return arrayEntityListCopy.stream().filter(entity -> entity.getId() == id).toList();
    }

    public List<ArrayEntity> findByArrayLengthGreaterThan(int threshold) {
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        return arrayEntityListCopy.stream().filter(entity -> entity.getData().length > threshold).toList();
    }

    public List<ArrayEntity> sortById() throws ArrayException {
        Comparator<ArrayEntity> comparator = ArrayEntity.getComparatorBy("id");
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        arrayEntityListCopy.sort(comparator);
        return arrayEntityListCopy;
    }

    public List<ArrayEntity> sortByDataLength() throws ArrayException {
        Comparator<ArrayEntity> comparator = ArrayEntity.getComparatorBy("dataLength");
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        arrayEntityListCopy.sort(comparator);
        return arrayEntityListCopy;
    }

    public List<ArrayEntity> sortByFirstDataElementLength() throws ArrayException {
        Comparator<ArrayEntity> comparator = ArrayEntity.getComparatorBy("firstElemLength");
        List<ArrayEntity> arrayEntityListCopy = new ArrayList<>(arrayEntityList);
        arrayEntityListCopy.sort(comparator);
        return arrayEntityListCopy;
    }

    public List<ArrayEntity> query(EntitySpecification specification) {
        return arrayEntityList.stream()
                .filter(specification::specify)
                .toList();
    }
}

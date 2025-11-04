package main.com.innowise.taskarray.repository.impl;

import main.com.innowise.taskarray.comparator.ArrayEntityComparator;
import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.repository.EntitySpecification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class ArrayEntityRepository {
    private static final Logger logger = Logger.getLogger(ArrayEntityRepository.class.getName());
    private static ArrayEntityRepository instance;
    private final List<ArrayEntity> arrayEntityList = new ArrayList<>();

    private ArrayEntityRepository() {
        logger.info("ArrayEntityRepository initialized");
    }

    public static ArrayEntityRepository getInstance() {
        if (instance == null) {
            logger.info("Creating new ArrayEntityRepository instance");
            instance = new ArrayEntityRepository();
        } else {
            logger.fine("Returning existing ArrayEntityRepository instance");
        }
        return instance;
    }

    public boolean add(ArrayEntity entity) {
        logger.fine("Adding entity with id=" + entity.getId());
        return arrayEntityList.add(entity);
    }

    public boolean addAll(List<ArrayEntity> entities) {
        logger.fine("Adding " + entities.size() + " entities");
        return arrayEntityList.addAll(entities);
    }

    public int indexOf(ArrayEntity entity) {
        int index = arrayEntityList.indexOf(entity);
        logger.fine("Index of entity id=" + entity.getId() + " is " + index);
        return index;
    }

    public boolean remove(ArrayEntity entity) {
        logger.fine("Removing entity with id=" + entity.getId());
        return arrayEntityList.remove(entity);
    }

    public ArrayEntity remove(int index) {
        logger.fine("Removing entity at index=" + index);
        return arrayEntityList.remove(index);
    }

    public List<ArrayEntity> findById(int id) {
        logger.fine("Finding entities with id=" + id);
        return arrayEntityList.stream()
                .filter(entity -> entity.getId() == id)
                .toList();
    }

    public List<ArrayEntity> findByArrayLengthGreaterThan(int threshold) {
        logger.fine("Finding entities with array length > " + threshold);
        return arrayEntityList.stream()
                .filter(entity -> entity.getData().length > threshold)
                .toList();
    }

    public List<ArrayEntity> sortById() throws ArrayException {
        logger.info("Sorting entities by ID");
        Comparator<ArrayEntity> comparator = ArrayEntityComparator.ID.getComparator();
        List<ArrayEntity> sorted = new ArrayList<>(arrayEntityList);
        sorted.sort(comparator);
        return sorted;
    }

    public List<ArrayEntity> sortByDataLength() throws ArrayException {
        logger.info("Sorting entities by data length");
        Comparator<ArrayEntity> comparator = ArrayEntityComparator.DATA.getComparator();
        List<ArrayEntity> sorted = new ArrayList<>(arrayEntityList);
        sorted.sort(comparator);
        return sorted;
    }

    public List<ArrayEntity> sortByFirstDataElementLength() throws ArrayException {
        logger.info("Sorting entities by first data element length");
        Comparator<ArrayEntity> comparator = ArrayEntityComparator.FIRST_ELEMENT.getComparator();
        List<ArrayEntity> sorted = new ArrayList<>(arrayEntityList);
        sorted.sort(comparator);
        return sorted;
    }

    public List<ArrayEntity> query(EntitySpecification specification) {
        logger.info("Querying repository with specification: " + specification.getClass().getSimpleName());
        return arrayEntityList.stream()
                .filter(specification::specify)
                .toList();
    }

    public void clear() {
        logger.info("Clearing repository");
        arrayEntityList.clear();
    }
}

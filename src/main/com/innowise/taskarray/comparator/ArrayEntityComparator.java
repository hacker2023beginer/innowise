package main.com.innowise.taskarray.comparator;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;

import java.util.Comparator;

public enum ArrayEntityComparator {
    ID, FIRST_ELEMENT, DATA;

    public Comparator<ArrayEntity> getComparator() throws ArrayException {
        switch (this) {
            case ID:
                return Comparator.comparingInt(ArrayEntity::getId);
            case FIRST_ELEMENT:
                return Comparator.comparing(
                        e -> e.getData().length > 0 ? e.getData()[0] : null,
                        Comparator.nullsFirst(Comparator.naturalOrder())
                );
            case DATA:
                return Comparator.comparingInt(e -> e.getData().length);
            default:
                throw new ArrayException("Unexpected value: " + this);
        }
    }

}

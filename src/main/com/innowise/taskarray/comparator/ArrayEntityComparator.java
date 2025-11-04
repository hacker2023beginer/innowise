package main.com.innowise.taskarray.comparator;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;

import java.util.Comparator;
import java.util.logging.Logger;

public enum ArrayEntityComparator {
    ID, FIRST_ELEMENT, DATA;

    private static final Logger logger = Logger.getLogger(ArrayEntityComparator.class.getName());

    public Comparator<ArrayEntity> getComparator() throws ArrayException {
        logger.info("getComparator() called for: " + this.name());

        switch (this) {
            case ID:
                logger.fine("Creating comparator by ID");
                return Comparator.comparingInt(ArrayEntity::getId);

            case FIRST_ELEMENT:
                logger.fine("Creating comparator by FIRST_ELEMENT");
                return Comparator.comparing(
                        e -> {
                            String[] data = e.getData();
                            String first = (data != null && data.length > 0) ? data[0] : null;
                            logger.finer("Entity id=" + e.getId() + ", first element=" + first);
                            return first;
                        },
                        Comparator.nullsFirst(Comparator.naturalOrder())
                );

            case DATA:
                logger.fine("Creating comparator by DATA length");
                return Comparator.comparingInt(e -> {
                    int len = e.getData().length;
                    logger.info("Entity id=" + e.getId() + ", data length=" + len);
                    return len;
                });

            default:
                logger.severe("Fatal error. Unexpected comparator type: " + this);
                throw new ArrayException("Unexpected value: " + this);
        }
    }
}

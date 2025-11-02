package main.com.innowise.taskarray.entity;

import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.observer.ArrayEntityObservable;
import main.com.innowise.taskarray.observer.impl.ArrayEntityObserver;
import main.com.innowise.taskarray.parser.EntityParser;
import main.com.innowise.taskarray.parser.impl.ArrayEntityParser;
import main.com.innowise.taskarray.validator.ArrayValidator;
import main.com.innowise.taskarray.validator.impl.StringArrayValidator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

public class ArrayEntity implements ArrayEntityObservable {

    private static final Logger logger = Logger.getLogger(ArrayEntity.class.getName());

    private int id = 0;
    private String[] data;
    private ArrayEntityObserver observer;

    private ArrayEntity() {
        logger.fine("ArrayEntity constructor called");
    }

    public String[] getData() {
        logger.fine("getData() called");
        String[] result = data.clone();
        return result;
    }

    public int getId() {
        logger.fine("getId() called");
        return id;
    }

    public void setEntityData(String[] data) throws ArrayException {
        if (data == null || data.length == 0) {
            logger.warning("Data is null or empty");
            throw new ArrayException("Array for set must have at least one correct element");
        }
        EntityParser parser = new ArrayEntityParser();
        String[] correctData = parser.parseStringDataToArrayEntityData(data);

        this.data = Arrays.copyOf(correctData, correctData.length);
        notifyObservers();
    }

    public void setValueAtIndexOf(String string, int index) {
        if (index >= 0 && index < data.length) {
            ArrayValidator validator = new StringArrayValidator();
            if (validator.isValidLine(string)) {
                this.data[index] = string;
                notifyObservers();
            }
        }
    }

    @Override
    public void attach(ArrayEntityObserver observer) {
        this.observer = observer;
    }

    @Override
    public void detach(ArrayEntityObserver observer) {
        this.observer = null;
    }

    @Override
    public void notifyObservers() {
        if (observer != null) {
            observer.onArrayChanged(this);
        }
    }

    public static Builder newBuilder() {
        logger.fine("newBuilder() called");
        return new ArrayEntity().new Builder();
    }

    public static Comparator<ArrayEntity> getComparatorBy(String type) throws ArrayException {
        return switch (type) {
            case "id" -> Comparator.comparingInt(ArrayEntity::getId);
            case "firstElemLength" -> Comparator.comparing(e -> e.getData().length > 0 ? e.getData()[0] : "");
            case "dataLength" -> Comparator.comparingInt(e -> e.getData().length);
            default -> throw new ArrayException("Unknown sort type: " + type);
        };
    }


    public class Builder {

        private Builder() {
            logger.fine("Builder constructor called");
        }

        public Builder setData(String[] data) throws ArrayException {
            logger.info("setData() called with: " + Arrays.toString(data));
            if (data == null || data.length == 0) {
                logger.warning("Data is null or empty");
                throw new ArrayException("Array for set must have at least one correct element");
            }
            EntityParser parser = new ArrayEntityParser();
            ArrayEntity.this.data = parser.parseStringDataToArrayEntityData(data);
            logger.fine("Data set successfully: " + Arrays.toString(ArrayEntity.this.data));
            return this;
        }

        public Builder setId(int id) {
            logger.info("setId() called with: " + id);
            ArrayEntity.this.id = id;
            return this;
        }

        public ArrayEntity build() throws ArrayException {
            logger.info("build() called");
            if (ArrayEntity.this.data == null || ArrayEntity.this.data.length == 0) {
                logger.warning("Build failed: data is null or empty");
                throw new ArrayException("Array must have at least one element");
            }
            logger.fine("ArrayEntity built successfully with id=" + ArrayEntity.this.id);
            return ArrayEntity.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        logger.fine("equals() called");
        if (this == o) return true;
        if (!(o instanceof ArrayEntity that)) return false;
        boolean result = id == that.id && Arrays.equals(data, that.data);
        logger.fine("equals() result: " + result);
        return result;
    }

    @Override
    public int hashCode() {
        logger.fine("hashCode() called");
        int result = 17;
        result = 31 * result + Arrays.hashCode(data);
        result = 31 * result + id;
        logger.fine("hashCode() result: " + result);
        return result;
    }

    @Override
    public String toString() {
        return "ArrayEntity{" +
                "data=" + Arrays.toString(data) +
                ", id=" + id +
                '}';
    }
}

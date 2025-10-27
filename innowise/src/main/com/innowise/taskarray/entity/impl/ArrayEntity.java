package main.com.innowise.taskarray.entity.impl;

import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.parser.EntityParser;
import main.com.innowise.taskarray.parser.impl.ArrayEntityParser;

import java.util.Arrays;
import java.util.logging.Logger;

public class ArrayEntity {

    private static final Logger logger = Logger.getLogger(ArrayEntity.class.getName());

    private String[] data;
    private int id = 0;

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

    public static Builder newBuilder() {
        logger.fine("newBuilder() called");
        return new ArrayEntity().new Builder();
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
}

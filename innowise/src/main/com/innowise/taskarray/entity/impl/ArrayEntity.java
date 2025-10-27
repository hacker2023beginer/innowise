package main.com.innowise.taskarray.entity.impl;

import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.validator.impl.StringArrayValidator;

import java.util.Arrays;
import java.util.Objects;

public class ArrayEntity {

    private String[] data;
    private int id;

    private ArrayEntity() {
        // private constructor
    }

    public String[] getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public static Builder newBuilder() {
        return new ArrayEntity().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setData(String[] data) throws ArrayException {
            if (data == null || data.length == 0) {
                throw new ArrayException("Array for set must have at least one correct element");
            }
            StringArrayValidator stringArrayValidator = new StringArrayValidator();
            if (stringArrayValidator.isValidTokens(data)) {
                ArrayEntity.this.data = data.clone();
                return this;
            }
            throw new ArrayException("Array contains invalid tokens");
        }

        public Builder setId(int id) {
            ArrayEntity.this.id = id;
            return this;
        }

        public ArrayEntity build() throws ArrayException {
            if (ArrayEntity.this.data == null || ArrayEntity.this.data.length == 0) {
                throw new ArrayException("Array must have at least one element");
            }
            return ArrayEntity.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArrayEntity that = (ArrayEntity) o;

        if (id != that.id) {
            return false;
        }

        if (data == null || that.data == null) {
            return data == that.data;
        }

        if (data.length != that.data.length) {
            return false;
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                if (that.data[i] != null) {
                    return false;
                }
            } else {
                if (!data[i].equals(that.data[i])) {
                    return false;
                }
            }
        }

        return true;
    }


    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Arrays.hashCode(data);
        result = 31 * result + id;
        return result;
    }

}



package test.com.innowise.taskarray.service;

import main.com.innowise.taskarray.entity.impl.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.service.impl.ArrayStringAverageService;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayStringAverageServiceTest {
    ArrayStringAverageService test = new ArrayStringAverageService();
    @Test
    public void testAverageOnNormalData() throws ArrayException {
        ArrayEntity arrayEntity = ArrayEntity.newBuilder().setData(new String[]{"abdc", "huhuhi"}).build();
        double actual = test.average(arrayEntity);
        double expected = 5.0;
        assertEquals(actual, expected, 0.01);
    }

    @Test
    public void testAverageException() {
        try {
            ArrayEntity arrayEntity = ArrayEntity.newBuilder().setData(new String[]{null, ""}).build();
            double actual = test.average(arrayEntity);
        } catch (ArrayException e) {
            e.printStackTrace();
        }
    }
}
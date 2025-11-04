package main.com.innowise.taskarray.repository;

import main.com.innowise.taskarray.entity.ArrayEntity;
import main.com.innowise.taskarray.exception.ArrayException;
import main.com.innowise.taskarray.repository.impl.ArrayEntityRepository;
import main.com.innowise.taskarray.repository.impl.ArrayEntityIdSpecification;
import main.com.innowise.taskarray.repository.impl.ArrayEntitySumGreaterThanSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayEntityRepositoryTest {

    private ArrayEntityRepository repository;

    @BeforeEach
    public void setup() {
        repository = ArrayEntityRepository.getInstance();
    }

    private ArrayEntity buildEntity(int id, String... data) throws ArrayException {
        return ArrayEntity.newBuilder()
                .setId(id)
                .setData(data)
                .build();
    }

    @Test
    public void testAddAndFindById() throws ArrayException {
        ArrayEntity entity = buildEntity(101, "a", "b");
        repository.add(entity);

        List<ArrayEntity> result = repository.findById(101);
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(101, result.get(0).getId())
        );
    }

    @Test
    public void testRemoveByEntity() throws ArrayException {
        ArrayEntity entity = buildEntity(202, "x", "y");
        repository.add(entity);
        boolean removed = repository.remove(entity);

        assertAll(
                () -> assertTrue(removed),
                () -> assertEquals(0, repository.findById(202).size())
        );
    }

    @Test
    public void testSortById() throws ArrayException {
        repository.add(buildEntity(3, "a"));
        repository.add(buildEntity(1, "b"));
        repository.add(buildEntity(2, "c"));

        List<ArrayEntity> sorted = repository.sortById();

        assertAll(
                () -> assertEquals(1, sorted.get(0).getId()),
                () -> assertEquals(2, sorted.get(1).getId()),
                () -> assertEquals(3, sorted.get(2).getId())
        );
    }

    @Test
    public void testQueryBySpecification() throws ArrayException {
        repository.add(buildEntity(10, "abc"));
        repository.add(buildEntity(20, "xyz"));

        List<ArrayEntity> result = repository.query(new ArrayEntityIdSpecification(20));

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(20, result.get(0).getId())
        );
    }

    @Test
    public void testFindByArrayLengthGreaterThan() throws ArrayException {
        repository.add(buildEntity(1, "a"));
        repository.add(buildEntity(2, "b", "c", "d"));

        List<ArrayEntity> result = repository.findByArrayLengthGreaterThan(2);

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(2, result.get(0).getId())
        );
    }

    @Test
    public void testRepositoryQueryWithSumSpecification() throws ArrayException {
        ArrayEntityRepository repo = ArrayEntityRepository.getInstance();
        repo.add(ArrayEntity.newBuilder().setId(1).setData(new String[]{"1", "2"}).build()); // sum = 2
        repo.add(ArrayEntity.newBuilder().setId(2).setData(new String[]{"10", "20"}).build()); // sum = 4

        EntitySpecification spec = new ArrayEntitySumGreaterThanSpecification(3);
        List<ArrayEntity> result = repo.query(spec);

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(2, result.get(0).getId())
        );
    }

}

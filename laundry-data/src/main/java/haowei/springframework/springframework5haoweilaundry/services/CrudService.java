package haowei.springframework.springframework5haoweilaundry.services;

import java.util.Set;

// add java generics to CrudService, like CrudRepository
public interface CrudService<T, ID> {
    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}

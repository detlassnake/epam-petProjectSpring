package ua.epam.petProjectSpring.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T save(T t);
    List<T> getAll();
    T getById(ID id);
    void update(ID id, T t);
    void deleteById(ID id);
}
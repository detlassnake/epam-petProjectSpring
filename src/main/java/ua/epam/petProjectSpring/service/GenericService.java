package ua.epam.petProjectSpring.service;

import java.util.List;

public interface GenericService<T,ID> {
    T create(T t);
    List<T> read();
    T readById(ID id);
    void update( ID id, T t);
    void delete(ID id);
}


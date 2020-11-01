package service;

import java.util.List;

public interface customerService<T> {
    List<T> findAll();
    void add(T object);
    void update (int id, T object);
    void delete(int id);
}

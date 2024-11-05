package org.example.intefaces;
import java.util.List;
public interface Repository {
    void save(T entity);
    void saveAll(List<T> entities);
    List<T> load();
}

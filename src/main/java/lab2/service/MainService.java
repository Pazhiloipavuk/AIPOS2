package lab2.service;

import java.util.List;
import java.util.Optional;

public interface MainService<E> {

    E save(E entity);

    Optional<E> findById(Long id);

    List<E> findAll();

    void delete(E entity);

    void deleteById(Long id);
}


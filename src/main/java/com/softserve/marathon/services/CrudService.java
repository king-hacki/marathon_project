package com.softserve.marathon.services;

import org.springframework.stereotype.Service;

@Service
public interface CrudService<E, D> {
    E getById(long id);
    E update(D dto);
    void deleteById(long id);
    E save(D dto);
}

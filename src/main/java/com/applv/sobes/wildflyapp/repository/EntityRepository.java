package com.applv.sobes.wildflyapp.repository;

import com.applv.sobes.wildflyapp.entity.BaseEntity;
import java.util.List;
import java.util.Optional;

public interface EntityRepository<T extends BaseEntity> {

    List<T> findAll();
    
    Optional<T> findById(Integer id);

    boolean existsById(Integer id);

    T save(T entity);

    void delete(T entity);

}

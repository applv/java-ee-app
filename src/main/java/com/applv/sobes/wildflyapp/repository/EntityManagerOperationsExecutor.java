package com.applv.sobes.wildflyapp.repository;

import com.applv.sobes.wildflyapp.entity.BaseEntity;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EntityManagerOperationsExecutor {

  <T> Optional<T> findBySql(@NotNull SqlQueries sqlQuery, Map<String, Object> params, @NotNull Class<T> clazz);

  <T> List<T> findAllBySql(@NotNull SqlQueries sqlQuery, Map<String, Object> params, @NotNull Class<T> clazz);
  <T> List<T> findAllBySql(@NotNull SqlQueries sqlQuery, @NotNull Class<T> clazz);

  <T> T merge(T entity);

  <T extends BaseEntity> void delete(@NotNull T entity);

}

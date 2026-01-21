package com.applv.sobes.wildflyapp.repository;


import com.applv.sobes.wildflyapp.entity.Topic;
import java.util.List;
import java.util.Optional;

public interface TopicRepository extends EntityRepository<Topic> {

  List<Topic> findAll();

  Optional<Topic> findByName(String name);

  List<Topic> findByNameStartsWithIgnoreCaseOrderByNameAsc(String name);
}
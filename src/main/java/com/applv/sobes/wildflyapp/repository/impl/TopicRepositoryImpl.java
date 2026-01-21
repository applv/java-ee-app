package com.applv.sobes.wildflyapp.repository.impl;

import static com.applv.sobes.wildflyapp.repository.SqlQueries.*;

import com.applv.sobes.wildflyapp.entity.Topic;
import com.applv.sobes.wildflyapp.repository.EntityManagerOperationsExecutor;
import com.applv.sobes.wildflyapp.repository.TopicRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class TopicRepositoryImpl implements TopicRepository {

  private final static Logger LOG = Logger.getLogger(TopicRepositoryImpl.class);

  private final EntityManagerOperationsExecutor emOperationsProvider;

  @Inject
  public TopicRepositoryImpl(EntityManagerOperationsExecutor emOperationsProvider) {
    this.emOperationsProvider = emOperationsProvider;
  }

  @Override
  public Optional<Topic> findByName(String name) {
    return emOperationsProvider.findBySql(GET_TOPIC_BY_NAME_QUERY, Map.of("name", name), Topic.class);
  }

  @Override
  public List<Topic> findByNameStartsWithIgnoreCaseOrderByNameAsc(String name) {
    return emOperationsProvider.findAllBySql(GET_TOPIC_BY_NAME_STARTS_WITH_IGNORE_CASE_ORDER_BY_NAME_ASC, Map.of("name", name),
        Topic.class);
  }

  @Override
  public Optional<Topic> findById(Integer id) {
    return emOperationsProvider.findBySql(GET_TOPIC_BY_ID_QUERY, Map.of("id", id), Topic.class);
  }

  @Override
  public boolean existsById(Integer id) {
    return emOperationsProvider.findBySql(GET_TOPIC_BY_ID_QUERY, Map.of("id", id), Topic.class).isPresent();
  }

  @Override
  public List<Topic> findAll() {
    var data = emOperationsProvider.findAllBySql(GET_ALL_TOPICS_QUERY, Topic.class);
    data.stream()
        .map(Object::toString)
        .forEach(LOG::info);

    return data;
  }

  @Override
  public Topic save(Topic entity) {
    return emOperationsProvider.merge(entity);
  }

  @Override
  public void delete(Topic entity) {
    emOperationsProvider.delete(entity);
  }

}

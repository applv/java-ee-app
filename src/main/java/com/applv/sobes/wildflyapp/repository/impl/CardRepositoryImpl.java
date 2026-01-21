package com.applv.sobes.wildflyapp.repository.impl;

import static com.applv.sobes.wildflyapp.repository.SqlQueries.*;

import com.applv.sobes.wildflyapp.repository.EntityManagerOperationsExecutor;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.applv.sobes.wildflyapp.entity.Card;
import com.applv.sobes.wildflyapp.repository.CardRepository;
import javax.inject.Inject;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

@ApplicationScoped
public class CardRepositoryImpl implements CardRepository {

  private final EntityManagerOperationsExecutor emOperationsProvider;

  @Inject
  public CardRepositoryImpl(EntityManagerOperationsExecutor emOperationsProvider) {
    this.emOperationsProvider = emOperationsProvider;
  }

  public List<Card> findAll() {
    throw new RuntimeException("The method findAll isn't defined");
  }

  @Override
  public List<Card> findAllByTopicName(String topicName) {
    Objects.requireNonNull(topicName, "topicName must not be null or blank");
    return emOperationsProvider.findAllBySql(GET_CARDS_BY_TOPIC_QUERY, Map.of("topicName", topicName), Card.class);
  }

  @Transactional
  @Override
  public List<Card> findAllByValue(String value) {
    Objects.requireNonNull(value, "value must not be null or blank");
    return StringUtils.isNoneBlank(value)
           ? emOperationsProvider.findAllBySql(GET_CARDS_BY_VALUE_QUERY, Map.of("value", value), Card.class)
           : List.of();
  }

  @Override
  public List<Card> findAllByTopicNameAndValue(String topicName, String value) {
    Objects.requireNonNull(topicName, "topicName must not be null or blank");
    Objects.requireNonNull(value, "value must not be null or blank");

    Map<String, Object> params = Map.of("topicName", topicName, "value", value);
    return emOperationsProvider.findAllBySql(GET_CARDS_BY_TOPIC_AND_VALUE_QUERY, params, Card.class);
  }

  @Override
  public Optional<Card> findById(Integer id) {
    Objects.requireNonNull(id, "id must not be null");
    List<Card> cards = emOperationsProvider.findAllBySql(GET_CARDS_BY_ID_QUERY, Map.of("id", id), Card.class);
    return cards.isEmpty() ? Optional.empty() : Optional.of(cards.get(0));
  }

  @Override
  public boolean existsById(Integer id) {
    return !emOperationsProvider.findAllBySql(GET_CARDS_BY_ID_QUERY, Map.of("id", id), Card.class).isEmpty();
  }

  @Transactional
  @Override
  public Card save(Card entity) {
    return emOperationsProvider.merge(entity);
  }

  @Transactional
  @Override
  public void delete(Card entity) {
    emOperationsProvider.delete(entity);
  }

}

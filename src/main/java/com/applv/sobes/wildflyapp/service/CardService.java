package com.applv.sobes.wildflyapp.service;

import com.applv.sobes.wildflyapp.dto.CardDto;
import java.util.List;
import java.util.Optional;

public interface CardService {

  List<CardDto> findAll();

  Optional<CardDto> findById(Integer id);

  List<CardDto> findAllByTopicName(String topicName);

  List<CardDto> findAllByValue(String value);

  List<CardDto> findAllByTopicNameAndValue(String topicName, String value);

  CardDto add(CardDto card);

  CardDto update(CardDto card);

  boolean delete(Integer id);

  void saveAll(List<CardDto> cards);
}

package com.applv.sobes.wildflyapp.repository;


import java.util.List;

import com.applv.sobes.wildflyapp.entity.Card;

public interface CardRepository extends EntityRepository<Card> {

    List<Card> findAllByTopicName(String topicName);

    List<Card> findAllByValue(String value);

    List<Card> findAllByTopicNameAndValue(String topicName, String value);
}
package com.applv.sobes.wildflyapp.repository;

import lombok.Getter;

public enum SqlQueries {

  GET_CARDS_BY_ID_QUERY("SELECT * FROM Card WHERE id = :id"),
  GET_CARDS_BY_TOPIC_QUERY("""
            SELECT c.*
              FROM Card c
              JOIN Topic t ON t.id = c.topic_id
             WHERE t.name = :topicName
             ORDER BY c.question
            """),
  GET_CARDS_BY_VALUE_QUERY("""
            SELECT c.*
              FROM Card c
             WHERE(lower(c.question) LIKE '%'||lower(:value)||'%'
                OR lower(c.answer)   LIKE '%'||lower(:value)||'%')
             ORDER BY c.question
            """),
  GET_CARDS_BY_TOPIC_AND_VALUE_QUERY("""
            SELECT c.*
              FROM Card c
              JOIN Topic t ON t.id = c.topic_id
             WHERE t.name = :topicName
               AND (  lower(c.question) LIKE '%'||lower(:value)||'%'
                   OR lower(c.answer)   LIKE '%'||lower(:value)||'%')
             ORDER BY c.question
            """),
  GET_ALL_TOPICS_QUERY("SELECT * FROM topic"),
  GET_TOPIC_BY_ID_QUERY("SELECT * FROM topic WHERE id = :id"),
  GET_TOPIC_BY_NAME_QUERY("SELECT * FROM topic WHERE name = :name"),
  GET_TOPIC_BY_NAME_STARTS_WITH_IGNORE_CASE_ORDER_BY_NAME_ASC("""
            SELECT *
              FROM topic
             WHERE lower(name) like concat(lower(:name), '%')
             ORDER BY question ASC
            """);

  @Getter
  private final String sql;

  SqlQueries(String sql) {
    this.sql = sql;
  }

}

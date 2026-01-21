package com.applv.sobes.wildflyapp.service;

import com.applv.sobes.wildflyapp.dto.TopicDto;
import java.util.List;
import java.util.Optional;

public interface TopicService {

  List<TopicDto> findAll();

  boolean exists(Integer id);

  Optional<TopicDto> findById(Integer id);

  Optional<TopicDto> findByName(String nameSubstr);

  List<TopicDto> findByNameStartsWithIgnoreCaseOrderByNameAsc(String nameSubstr);

  Optional<TopicDto> add(TopicDto topic);

  Optional<TopicDto> update(TopicDto topic);

  boolean delete(Integer id);
}

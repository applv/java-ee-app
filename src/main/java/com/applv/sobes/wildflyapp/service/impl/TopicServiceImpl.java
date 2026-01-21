package com.applv.sobes.wildflyapp.service.impl;

import com.applv.sobes.wildflyapp.dto.TopicDto;
import com.applv.sobes.wildflyapp.mapper.TopicMapper;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.applv.sobes.wildflyapp.entity.Topic;
import com.applv.sobes.wildflyapp.repository.TopicRepository;
import com.applv.sobes.wildflyapp.service.TopicService;

import java.util.Objects;

@Named("topicService")
@ApplicationScoped
public class TopicServiceImpl implements TopicService {

  private final TopicRepository topicRepository;

  private final TopicMapper topicMapper;

  @Inject
  public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper) {
    this.topicRepository = topicRepository;
    this.topicMapper = topicMapper;
  }

  @Transactional
  @Override
  public List<TopicDto> findAll() {
      return topicMapper.mapToDto(topicRepository.findAll());
  }

  @Transactional
  @Override
  public boolean exists(Integer id) {
    return topicRepository.existsById(id);
  }

  @Transactional
  @Override
  public Optional<TopicDto> findById(Integer id) {
    return topicRepository.findById(id)
        .map(topicMapper::mapToDto);
  }

  @Transactional
  @Override
  public Optional<TopicDto> findByName(String name) {
    return topicRepository.findByName(name)
        .map(topicMapper::mapToDto);
  }

  @Transactional
  @Override
  public List<TopicDto> findByNameStartsWithIgnoreCaseOrderByNameAsc(String nameSubstr) {
    return topicMapper.mapToDto(topicRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(nameSubstr));
  }

  @Transactional
  @Override
  public Optional<TopicDto> add(TopicDto dto) {
    if (Objects.nonNull(dto.getId())) {
      throw new IllegalArgumentException("Topic is exist");
    }
    isValidTopic(dto);

    Topic parent = null;
    if (Objects.nonNull(dto.getParent())) {
      parent = Objects.nonNull(dto.getParent().getId())
          ? topicRepository.findById(dto.getParent().getId())
                           .orElseThrow(() -> new RuntimeException("Topic parent is not exist: id=" + dto.getParent().getId()))
          : add(dto.getParent())
              .map(topicMapper::mapToTopic)
              .orElse(null);
    }

    return Optional.ofNullable(topicRepository.save(
        Topic
            .builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .parent(parent)
            .build()))
        .map(topicMapper::mapToDto);
  }

  @Transactional
  @Override
  public Optional<TopicDto> update(TopicDto dto) {
    isValidTopic(dto);
    if (Objects.isNull(dto.getId())) {
      throw new IllegalArgumentException("Topic is not valid");
    }

    return Optional.ofNullable(topicRepository.save(topicMapper.mapToTopic(dto))).map(topicMapper::mapToDto);
  }

  @Transactional
  @Override
  public boolean delete(Integer id) {
    Topic topic = topicRepository.findById(id).orElse(null);
    if (Objects.nonNull(topic)) {
      topicRepository.delete(topic);
      return true;
    } else {
      return false;
    }
  }

  private void isValidTopic(TopicDto dto) {
    if (Objects.nonNull(dto) && StringUtils.isNotBlank(dto.getName())) {
      throw new IllegalArgumentException("Topic is not valid");
    }
  }
}

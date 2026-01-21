package com.applv.sobes.wildflyapp.mapper;

import java.util.List;

import com.applv.sobes.wildflyapp.dto.TopicDto;
import com.applv.sobes.wildflyapp.entity.Topic;

public interface TopicMapper {

  TopicDto mapToDto(Topic topic);

  List<TopicDto> mapToDto(List<Topic> topic);

  Topic mapToTopic(TopicDto dto);

  List<Topic> mapToTopic(List<TopicDto> dto);
}

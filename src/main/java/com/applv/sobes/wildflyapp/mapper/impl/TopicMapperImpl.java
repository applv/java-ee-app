package com.applv.sobes.wildflyapp.mapper.impl;


import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.applv.sobes.wildflyapp.dto.TopicDto;
import com.applv.sobes.wildflyapp.entity.HasParent;
import com.applv.sobes.wildflyapp.entity.Topic;
import com.applv.sobes.wildflyapp.mapper.TopicMapper;

@ApplicationScoped
public class TopicMapperImpl implements TopicMapper {

  @Override
    public TopicDto mapToDto(Topic topic) {
        if (Objects.nonNull(topic)) {
            validate(topic);
            return TopicDto
                .builder()
                .id(topic.getId())
                .name(topic.getName())
                .description(topic.getDescription())
                .parent(Objects.nonNull(topic.getParent()) ? mapToDto(topic.getParent()) : null)
                .build();
        }
        return null;
    }

    @Override
    public List<TopicDto> mapToDto(List<Topic> list) {
        if(Objects.isNull(list)) {
            return List.of();
        }
        return list.stream().map(this::mapToDto).toList();
    }

    @Override
    public Topic mapToTopic(TopicDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        validate(dto);

        return Topic
            .builder()
            .id(dto.getId())
            .name(dto.getName())
            .description(dto.getDescription())
            .parent(mapToTopic(dto.getParent()))
            .build();
    }

    @Override
    public List<Topic> mapToTopic(List<TopicDto> dto) {
        if (Objects.isNull(dto)) {
            return null;
        }

        return dto.stream()
                  .map(this::mapToTopic)
                  .toList();
    }

    private void validate(HasParent entity) {
        var parents = new ArrayList<HasParent>();
        for(var parent = entity.getParent(); Objects.nonNull(parent); parent = parent.getParent()) {
            if (parents.contains(parent)) {
                throw new IllegalArgumentException("Topic has circular parent reference: " + entity);
            }
            parents.add(parent);
        }
    }
}

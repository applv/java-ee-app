package com.applv.sobes.wildflyapp.mapper.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.applv.sobes.wildflyapp.dto.CardDto;
import com.applv.sobes.wildflyapp.dto.TopicDto;
import com.applv.sobes.wildflyapp.entity.Card;
import com.applv.sobes.wildflyapp.entity.Topic;
import com.applv.sobes.wildflyapp.mapper.CardMapper;
import com.applv.sobes.wildflyapp.mapper.TopicMapper;


@ApplicationScoped
public class CardMapperImpl implements CardMapper {

    private final TopicMapper topicMapper;

    @Inject
    public CardMapperImpl(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Override
    public CardDto mapToDto(Card card) {
        var list = mapToDto(List.of(card));
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
    public List<CardDto> mapToDto(List<Card> list) {
        if(Objects.isNull(list)) {
            return null;
        }
        Map<Integer, TopicDto> topicDtoList = new HashMap<>();
        list.stream()
            .filter(Objects::nonNull)
            .map(Card::getTopic)
            .filter(Objects::nonNull)
            .distinct()
            .forEach(topic -> topicDtoList.computeIfAbsent(topic.getId(), k -> topicMapper.mapToDto(topic)));

        return list.stream()
                   .filter(Objects::nonNull)
                   .map(card -> CardDto
                            .builder()
                            .id(card.getId())
                            .question(card.getQuestion())
                            .answer(card.getAnswer())
                            .topicDto(topicDtoList.get(card.getTopic().getId()))
                            .build())
                   .toList();
    }

    @Override
    public Card mapToCard(CardDto cardDto) {
        var list = mapToCard(List.of(cardDto));
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
    public List<Card> mapToCard(List<CardDto> cardDtoList) {
        if (CollectionUtils.isEmpty(cardDtoList)) {
            return null;
        }
        Map<Integer, Topic> topics = new HashMap<>();
        cardDtoList
            .stream()
            .filter(Objects::nonNull)
            .map(CardDto::getTopicDto)
            .filter(Objects::nonNull)
            .distinct()
            .forEach(topicDto -> topics.computeIfAbsent(topicDto.getId(), k -> topicMapper.mapToTopic(topicDto)));

        return cardDtoList
            .stream()
            .filter(Objects::nonNull)
            .map(dto -> Card
                .builder()
                .id(dto.getId())
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .topic(topics.get(dto.getTopicDto().getId()))
                .build())
            .map(o -> (Card) o)
            .toList();
    }
}

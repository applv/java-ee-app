package com.applv.sobes.wildflyapp.service.impl;

import com.applv.sobes.wildflyapp.dto.CardDto;
import com.applv.sobes.wildflyapp.mapper.CardMapper;
import javax.inject.Named;
import javax.transaction.Transactional;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.applv.sobes.wildflyapp.repository.CardRepository;
import com.applv.sobes.wildflyapp.repository.TopicRepository;
import com.applv.sobes.wildflyapp.service.CardService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;

@Named("cardService")
@ApplicationScoped
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;

  private final TopicRepository topicRepository;

  private final CardMapper cardMapper;

  @Inject
  public CardServiceImpl(CardRepository cardRepository, TopicRepository topicRepository, CardMapper cardMapper) {
    this.cardRepository  = cardRepository;
    this.topicRepository = topicRepository;
    this.cardMapper      = cardMapper;
  }

  @Transactional
  @Override
  public List<CardDto> findAll() {
    return cardMapper.mapToDto(cardRepository.findAll());
  }

  @Transactional
  @Override
  public Optional<CardDto> findById(Integer id) {
    return cardRepository.findById(id).map(cardMapper::mapToDto);
  }

  @Transactional
  @Override
  public List<CardDto> findAllByTopicNameAndValue(String topicName, String value) {
    return cardMapper.mapToDto(cardRepository.findAllByTopicNameAndValue(topicName, value));
  }

  @Transactional
  @Override
  public List<CardDto> findAllByTopicName(String topicName) {
    return cardMapper.mapToDto(cardRepository.findAllByTopicName(topicName));
  }

  @Transactional
  @Override
  public List<CardDto> findByValue(String value) {
    return cardMapper.mapToDto(cardRepository.findAllByValue(value));
  }

  @Transactional
  @Override
  public CardDto add(CardDto dto) {
    validate(dto);

    if (Objects.nonNull(dto.getId())) {
      throw new IllegalArgumentException("Card is exist");
    }

    return Optional.ofNullable(cardRepository.save(cardMapper.mapToCard(dto)))
        .map(cardMapper::mapToDto)
        .orElseThrow(() -> new RuntimeException("Card " + dto + " wasn't created."));
  }

  @Transactional
  @Override
  public CardDto update(CardDto dto) {
    validate(dto);
    if (Objects.nonNull(dto.getId())) {
      throw new IllegalArgumentException("Card ID is cannot be null.");
    }

    return Optional.ofNullable(cardRepository.save(cardMapper.mapToCard(dto)))
        .map(cardMapper::mapToDto)
        .orElseThrow(() -> new RuntimeException("Card " + dto + " wasn't updated."));
  }

  @Transactional
  @Override
  public boolean delete(Integer id) {
    var card = cardRepository.findById(id).orElse(null);
    if (Objects.nonNull(card)) {
      cardRepository.delete(card);
      return true;

    } else {
      return false;
    }
  }

  @Transactional
  @Override
  public void saveAll(List<CardDto> cards) {
    cards
        .stream()
        .map(cardMapper::mapToCard)
        .forEach(cardRepository::save);
  }

  private void validate(CardDto card) {
    if (Objects.isNull(card)
        || StringUtils.isBlank(card.getQuestion())
        || StringUtils.isBlank(card.getAnswer())
        || Objects.isNull(card.getTopicDto())
        || !topicRepository.existsById(card.getTopicDto().getId())
        || Objects.isNull(card.getTopicDto().getId())) {
      throw new IllegalArgumentException("Card is not valid");
    }
  }
}

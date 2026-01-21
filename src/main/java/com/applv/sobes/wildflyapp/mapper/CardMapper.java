package com.applv.sobes.wildflyapp.mapper;

import java.util.List;

import com.applv.sobes.wildflyapp.dto.CardDto;
import com.applv.sobes.wildflyapp.entity.Card;

public interface CardMapper {

    CardDto mapToDto(Card card);

    List<CardDto> mapToDto(List<Card> cardList);

    Card mapToCard(CardDto cardDto);

    List<Card> mapToCard(List<CardDto> cardDtoList);
}

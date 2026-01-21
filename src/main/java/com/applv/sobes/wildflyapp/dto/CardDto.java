package com.applv.sobes.wildflyapp.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for {@link com.applv.sobesdb.entity.Card}
 */
@Builder
@Data
public class CardDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 23389612866203526L;

  private Integer id;
  private String question;
  private String answer;
  private TopicDto topicDto;
}
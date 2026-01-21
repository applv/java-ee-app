package com.applv.sobes.wildflyapp.dto;


import java.io.Serial;
import java.io.Serializable;

import com.applv.sobes.wildflyapp.entity.HasParent;

import lombok.Builder;
import lombok.Data;

/**
 * DTO for {@link com.applv.sobesdb.entity.Topic}
 */
@Builder
@Data
public class TopicDto implements HasParent, Serializable {
	
    @Serial
    private static final long serialVersionUID = -5170163076354216212L;
	
    private Integer id;
    private String name;
    private String description;
    private TopicDto parent;
}
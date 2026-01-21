package com.applv.sobes.wildflyapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

/**
 *
 * @author applv
 */
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "topic", uniqueConstraints = {@UniqueConstraint(name = "uc_topic_name", columnNames = {"name"})})
public class Topic extends BaseEntity implements HasParent, Serializable {
    
    @Serial
    private static final long serialVersionUID = -5170163076354216212L;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1024, unique = true)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_topic_id")
    @ToString.Exclude
    private Topic parent;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "parent")
    private Set<Topic> childTopics = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        var that = (Topic) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
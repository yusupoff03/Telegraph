package com.example.telegraph.entity;

import com.example.telegraph.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostEntity extends BaseEntity{
    private String name;
    private String title;
    private String body;
    @Column(unique = true)
    private String link;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity owner;
}

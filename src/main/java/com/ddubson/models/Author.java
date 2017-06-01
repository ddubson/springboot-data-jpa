package com.ddubson.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authors")
@Data
@Builder
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
}

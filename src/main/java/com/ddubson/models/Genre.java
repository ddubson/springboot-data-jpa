package com.ddubson.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
@Data
@Builder
public class Genre {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}

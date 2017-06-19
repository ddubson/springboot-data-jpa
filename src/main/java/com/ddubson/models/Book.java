package com.ddubson.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_genres",
            joinColumns = {
                    @JoinColumn(name = "book_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "genre_id",
                    nullable = false, updatable = false)})
    List<Genre> genres;
}

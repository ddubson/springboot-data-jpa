package com.ddubson.models

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(@Id @GeneratedValue
                val id: Long,
                val title: String,
                @ManyToOne(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
                val author: Author,
                @ManyToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
                @JoinTable(name = "books_genres",
                        joinColumns = arrayOf(JoinColumn(name = "book_id", nullable = false, updatable = false)),
                        inverseJoinColumns = arrayOf(JoinColumn(name = "genre_id", nullable = false, updatable = false)))
                val genres: List<Genre>) : Serializable
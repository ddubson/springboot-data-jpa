package com.ddubson.models

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "authors")
data class Author(@Id @GeneratedValue val id: Long,
                  val firstName: String,
                  val lastName: String,
                  @OneToMany(mappedBy = "author") val books: List<Book>) : Serializable
package com.ddubson.models

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "genres")
data class Genre(@Id @GeneratedValue val id: Long,
                 val name: String) : Serializable {
}
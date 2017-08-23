package com.ddubson.models

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "service_requests")
data class ServiceRequest(@Id @GeneratedValue val uniqueKey: Long,
                          val addressType: String,
                          val agency: String) : Serializable
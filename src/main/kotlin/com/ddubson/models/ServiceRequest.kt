package com.ddubson.models

import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "service_requests")
data class ServiceRequest(@Id @GeneratedValue val uniqueKey: Long,
                          val createdDate: Timestamp?,
                          val closedDate: Timestamp?,
                          val addressType: String,
                          val agencyName: String,
                          val agency: String) : Serializable
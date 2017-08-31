package com.ddubson.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "service_requests")
@JsonIgnoreProperties(ignoreUnknown = true)
data class ServiceRequest(@JsonProperty("unique_key") @Id @GeneratedValue val uniqueKey: Long,
                          @JsonProperty("created_date") val createdDate: Timestamp?,
                          @JsonProperty("closed_date") val closedDate: Timestamp?,
                          @JsonProperty("address_type") val addressType: String?,
                          @JsonProperty("agency_name") val agencyName: String?,
                          val agency: String?) : Serializable
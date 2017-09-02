package com.ddubson.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "service_requests")
@JsonIgnoreProperties(ignoreUnknown = true)
data class ServiceRequest(@JsonProperty("unique_key") @Id @GeneratedValue @Column(name="uniqueKey") val uniqueKey: Long,
                          @JsonProperty("created_date") @Column(name="createdDate") val createdDate: Timestamp?,
                          @JsonProperty("closed_date") @Column(name="closedDate") val closedDate: Timestamp?,
                          @JsonProperty("address_type") @Column(name="addressType") val addressType: String?,
                          @JsonProperty("agency_name") @Column(name="agencyName") val agencyName: String?,
                          val agency: String?) : Serializable
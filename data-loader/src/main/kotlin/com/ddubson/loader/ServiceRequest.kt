package com.ddubson.loader

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

@JsonIgnoreProperties(ignoreUnknown = true)
data class ServiceRequest(@JsonProperty("unique_key") val uniqueKey: Long,
                          @JsonProperty("created_date") val createdDate: Timestamp?,
                          @JsonProperty("closed_date") val closedDate: Timestamp?,
                          @JsonProperty("address_type") val addressType: String?,
                          @JsonProperty("agency_name") val agencyName: String?,
                          val agency: String?)
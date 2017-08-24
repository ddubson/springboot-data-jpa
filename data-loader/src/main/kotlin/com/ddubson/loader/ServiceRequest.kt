package com.ddubson.loader

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ServiceRequest(@JsonProperty("unique_key") val uniqueKey: Long,
                          @JsonProperty("address_type") val addressType: String?,
                          val agency: String?)
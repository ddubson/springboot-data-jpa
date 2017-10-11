package com.ddubson.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "service_requests")
@JsonIgnoreProperties(ignoreUnknown = true)
data class ServiceRequest(@JsonProperty("unique_key") @Id @GeneratedValue @Column(name = "uniqueKey") val uniqueKey: Long,
                          @JsonProperty("created_date") @Column(name = "createdDate") val createdDate: Timestamp?,
                          @JsonProperty("closed_date") @Column(name = "closedDate") val closedDate: Timestamp?,
                          @JsonProperty("address_type") @Column(name = "addressType") val addressType: String?,
                          @JsonProperty("agency_name") @Column(name = "agencyName") val agencyName: String?,
                          val agency: String?,
                          @JsonProperty("complaint_type") @Column(name = "complaintType") val complaintType: String?,
                          val descriptor: String?,
                          @JsonProperty("location_type") @Column(name = "locationType") val locationType: String?,
                          @JsonProperty("incident_type") @Column(name = "incidentType") val incidentType: String?,
                          @JsonProperty("incident_zip") @Column(name = "incidentZip") val incidentZip: String?,
                          @JsonProperty("incident_address") @Column(name = "incidentAddress") val incidentAddress: String?,
                          @JsonProperty("street_name") @Column(name = "streetName") val streetName: String?,
                          @JsonProperty("cross_street_1") @Column(name = "crossStreet1") val crossStreet1: String?,
                          @JsonProperty("cross_street_2") @Column(name = "crossStreet2") val crossStreet2: String?,
                          @JsonProperty("intersection_street_1") @Column(name = "intersectionStreet1") val intersectionStreet1: String?,
                          @JsonProperty("intersection_street_2") @Column(name = "intersectionStreet2") val intersectionStreet2: String?,
                          val city: String?,
                          val landmark: String?,
                          @JsonProperty("facility_type") @Column(name = "facilityType") val facilityType: String?,
                          val status: String?
) : Serializable
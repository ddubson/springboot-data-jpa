package com.ddubson.repositories

import com.ddubson.models.ServiceRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceRequestRepository: CrudRepository<ServiceRequest, Long> {
    override fun findAll(): List<ServiceRequest>
}
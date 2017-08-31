package com.ddubson.controllers

import com.ddubson.models.ServiceRequest
import com.ddubson.repositories.ServiceRequestRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ServiceRequestController(val repository: ServiceRequestRepository) {
    @GetMapping("/serviceRequests")
    fun getAllServiceRequests(): List<ServiceRequest> {
        return repository.findAll()
    }
}
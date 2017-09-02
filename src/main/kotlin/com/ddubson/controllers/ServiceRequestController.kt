package com.ddubson.controllers

import com.ddubson.repositories.ServiceRequestRepository
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ServiceRequestController(val repository: ServiceRequestRepository) {
    @GetMapping("/serviceRequests", produces = arrayOf("application/json"))
    fun getAllServiceRequests(): ResponseEntity<*> {
        return ok(repository.findAll())
    }
}
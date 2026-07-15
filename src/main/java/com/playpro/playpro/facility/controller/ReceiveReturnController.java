package com.playpro.playpro.facility.controller;

import com.playpro.playpro.facility.service.ReceiveReturnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/facility/facilities/{facilityId}/receive-return")
public class ReceiveReturnController {

    private final ReceiveReturnService receiveReturnService;

    public ReceiveReturnController(ReceiveReturnService receiveReturnService) {
        this.receiveReturnService = receiveReturnService;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> stub(@PathVariable String facilityId) {
        return ResponseEntity.ok(Collections.singletonMap("message", receiveReturnService.placeholder()));
    }
}

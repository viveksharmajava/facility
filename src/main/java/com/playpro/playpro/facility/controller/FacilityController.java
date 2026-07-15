package com.playpro.playpro.facility.controller;

import com.playpro.playpro.facility.dto.FacilityDto;
import com.playpro.playpro.facility.dto.FacilitySearchRequest;
import com.playpro.playpro.facility.dto.FacilitySummaryDto;
import com.playpro.playpro.facility.service.FacilityService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facility/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping("/find")
    public ResponseEntity<Page<FacilitySummaryDto>> findFacilities(@RequestBody FacilitySearchRequest request) {
        return ResponseEntity.ok(facilityService.findFacilities(request));
    }

    @GetMapping
    public ResponseEntity<List<FacilitySummaryDto>> listFacilities() {
        return ResponseEntity.ok(facilityService.listFacilities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto> getFacility(@PathVariable("id") String facilityId) {
        return ResponseEntity.ok(facilityService.getFacility(facilityId));
    }

    @PostMapping
    public ResponseEntity<FacilityDto> createFacility(@RequestBody FacilityDto dto) {
        return ResponseEntity.ok(facilityService.createFacility(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityDto> updateFacility(@PathVariable("id") String facilityId,
                                                      @RequestBody FacilityDto dto) {
        return ResponseEntity.ok(facilityService.updateFacility(facilityId, dto));
    }
}

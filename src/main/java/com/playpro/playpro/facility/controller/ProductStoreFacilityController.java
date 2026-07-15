package com.playpro.playpro.facility.controller;

import com.playpro.playpro.facility.dto.ProductStoreFacilityDto;
import com.playpro.playpro.facility.service.ProductStoreFacilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facility")
public class ProductStoreFacilityController {

    private final ProductStoreFacilityService productStoreFacilityService;

    public ProductStoreFacilityController(ProductStoreFacilityService productStoreFacilityService) {
        this.productStoreFacilityService = productStoreFacilityService;
    }

    @GetMapping("/product-stores/{storeId}/facilities")
    public ResponseEntity<List<ProductStoreFacilityDto>> listByStore(@PathVariable String storeId) {
        return ResponseEntity.ok(productStoreFacilityService.listByStore(storeId));
    }

    @GetMapping("/facilities/{facilityId}/stores")
    public ResponseEntity<List<ProductStoreFacilityDto>> listByFacility(@PathVariable String facilityId) {
        return ResponseEntity.ok(productStoreFacilityService.listByFacility(facilityId));
    }

    @PostMapping("/product-store-facilities")
    public ResponseEntity<ProductStoreFacilityDto> addMapping(@RequestBody ProductStoreFacilityDto dto) {
        return ResponseEntity.ok(productStoreFacilityService.addMapping(dto));
    }

    @DeleteMapping("/product-store-facilities")
    public ResponseEntity<Void> removeMapping(@RequestBody ProductStoreFacilityDto dto) {
        productStoreFacilityService.removeMapping(dto);
        return ResponseEntity.noContent().build();
    }
}

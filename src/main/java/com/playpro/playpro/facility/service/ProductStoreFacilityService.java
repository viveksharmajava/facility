package com.playpro.playpro.facility.service;

import com.playpro.playpro.facility.dto.ProductStoreFacilityDto;

import java.util.List;

public interface ProductStoreFacilityService {

    List<ProductStoreFacilityDto> listByStore(String productStoreId);

    List<ProductStoreFacilityDto> listByFacility(String facilityId);

    ProductStoreFacilityDto addMapping(ProductStoreFacilityDto dto);

    void removeMapping(ProductStoreFacilityDto dto);
}

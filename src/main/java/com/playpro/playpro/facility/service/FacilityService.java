package com.playpro.playpro.facility.service;

import com.playpro.playpro.facility.dto.FacilityDto;
import com.playpro.playpro.facility.dto.FacilitySearchRequest;
import com.playpro.playpro.facility.dto.FacilitySummaryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FacilityService {

    Page<FacilitySummaryDto> findFacilities(FacilitySearchRequest request);

    FacilityDto getFacility(String facilityId);

    List<FacilitySummaryDto> listFacilities();

    FacilityDto createFacility(FacilityDto dto);

    FacilityDto updateFacility(String facilityId, FacilityDto dto);
}

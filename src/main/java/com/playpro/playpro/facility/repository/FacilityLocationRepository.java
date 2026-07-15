package com.playpro.playpro.facility.repository;

import com.playpro.playpro.facility.entity.FacilityLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityLocationRepository extends JpaRepository<FacilityLocation, FacilityLocation.FacilityLocationId> {

    List<FacilityLocation> findByFacilityId(String facilityId);
}

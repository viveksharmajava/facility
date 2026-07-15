package com.playpro.playpro.facility.repository;

import com.playpro.playpro.facility.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FacilityRepository extends JpaRepository<Facility, String>, JpaSpecificationExecutor<Facility> {
}

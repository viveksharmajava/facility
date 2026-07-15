package com.playpro.playpro.facility.repository;

import com.playpro.playpro.facility.entity.ProductStoreFacility;
import com.playpro.playpro.facility.entity.ProductStoreFacilityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductStoreFacilityRepository extends JpaRepository<ProductStoreFacility, ProductStoreFacilityId> {

    List<ProductStoreFacility> findByIdProductStoreId(String productStoreId);

    List<ProductStoreFacility> findByIdFacilityId(String facilityId);
}

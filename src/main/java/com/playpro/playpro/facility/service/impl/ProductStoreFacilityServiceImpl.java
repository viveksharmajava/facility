package com.playpro.playpro.facility.service.impl;

import com.playpro.playpro.facility.dto.ProductStoreFacilityDto;
import com.playpro.playpro.facility.entity.Facility;
import com.playpro.playpro.facility.entity.ProductStoreFacility;
import com.playpro.playpro.facility.entity.ProductStoreFacilityId;
import com.playpro.playpro.facility.exception.ResourceNotFoundException;
import com.playpro.playpro.facility.mapper.FacilityMapper;
import com.playpro.playpro.facility.repository.FacilityRepository;
import com.playpro.playpro.facility.repository.ProductStoreFacilityRepository;
import com.playpro.playpro.facility.service.ProductStoreFacilityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductStoreFacilityServiceImpl implements ProductStoreFacilityService {

    private final ProductStoreFacilityRepository productStoreFacilityRepository;
    private final FacilityRepository facilityRepository;

    public ProductStoreFacilityServiceImpl(ProductStoreFacilityRepository productStoreFacilityRepository,
                                             FacilityRepository facilityRepository) {
        this.productStoreFacilityRepository = productStoreFacilityRepository;
        this.facilityRepository = facilityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductStoreFacilityDto> listByStore(String productStoreId) {
        LocalDateTime now = LocalDateTime.now();
        return productStoreFacilityRepository.findByIdProductStoreId(productStoreId).stream()
                .filter(m -> m.getThruDate() == null || m.getThruDate().isAfter(now))
                .map(m -> toDto(m))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductStoreFacilityDto> listByFacility(String facilityId) {
        loadFacility(facilityId);
        LocalDateTime now = LocalDateTime.now();
        return productStoreFacilityRepository.findByIdFacilityId(facilityId).stream()
                .filter(m -> m.getThruDate() == null || m.getThruDate().isAfter(now))
                .map(m -> toDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public ProductStoreFacilityDto addMapping(ProductStoreFacilityDto dto) {
        if (!StringUtils.hasText(dto.getProductStoreId())) {
            throw new IllegalArgumentException("productStoreId is required");
        }
        if (!StringUtils.hasText(dto.getFacilityId())) {
            throw new IllegalArgumentException("facilityId is required");
        }
        Facility facility = loadFacility(dto.getFacilityId());
        LocalDateTime fromDate = dto.getFromDate() != null ? dto.getFromDate() : LocalDateTime.now();

        ProductStoreFacilityId id = new ProductStoreFacilityId(dto.getProductStoreId(), dto.getFacilityId(), fromDate);
        if (productStoreFacilityRepository.existsById(id)) {
            throw new IllegalArgumentException("Mapping already exists for store, facility, and fromDate");
        }

        ProductStoreFacility entity = new ProductStoreFacility();
        entity.setId(id);
        entity.setThruDate(dto.getThruDate());
        entity.setSequenceNum(dto.getSequenceNum());
        ProductStoreFacility saved = productStoreFacilityRepository.save(entity);
        return FacilityMapper.toProductStoreFacilityDto(saved, facility);
    }

    @Override
    public void removeMapping(ProductStoreFacilityDto dto) {
        if (!StringUtils.hasText(dto.getProductStoreId()) || !StringUtils.hasText(dto.getFacilityId()) || dto.getFromDate() == null) {
            throw new IllegalArgumentException("productStoreId, facilityId, and fromDate are required");
        }
        ProductStoreFacilityId id = new ProductStoreFacilityId(dto.getProductStoreId(), dto.getFacilityId(), dto.getFromDate());
        ProductStoreFacility entity = productStoreFacilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product store facility mapping not found"));
        entity.setThruDate(LocalDateTime.now());
        productStoreFacilityRepository.save(entity);
    }

    private ProductStoreFacilityDto toDto(ProductStoreFacility mapping) {
        Facility facility = facilityRepository.findById(mapping.getId().getFacilityId()).orElse(null);
        return FacilityMapper.toProductStoreFacilityDto(mapping, facility);
    }

    private Facility loadFacility(String facilityId) {
        return facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found: " + facilityId));
    }
}

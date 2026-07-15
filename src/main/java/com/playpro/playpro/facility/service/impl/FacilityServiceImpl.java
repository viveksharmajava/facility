package com.playpro.playpro.facility.service.impl;

import com.playpro.playpro.facility.dto.FacilityDto;
import com.playpro.playpro.facility.dto.FacilitySearchRequest;
import com.playpro.playpro.facility.dto.FacilitySummaryDto;
import com.playpro.playpro.facility.entity.Facility;
import com.playpro.playpro.facility.exception.ResourceNotFoundException;
import com.playpro.playpro.facility.mapper.FacilityMapper;
import com.playpro.playpro.facility.repository.FacilityRepository;
import com.playpro.playpro.facility.repository.FacilityTypeRepository;
import com.playpro.playpro.facility.search.TextMatchMode;
import com.playpro.playpro.facility.service.FacilityService;
import com.playpro.playpro.facility.util.FacilityIdGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityTypeRepository facilityTypeRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository,
                                 FacilityTypeRepository facilityTypeRepository) {
        this.facilityRepository = facilityRepository;
        this.facilityTypeRepository = facilityTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FacilitySummaryDto> findFacilities(FacilitySearchRequest request) {
        PageRequest pageRequest = PageRequest.of(
                Math.max(request.getPage(), 0),
                Math.max(request.getSize(), 1),
                Sort.by(Sort.Direction.ASC, "facilityName"));
        Page<Facility> page = facilityRepository.findAll(buildSpecification(request), pageRequest);
        List<FacilitySummaryDto> content = page.getContent().stream()
                .map(FacilityMapper::toSummaryDto)
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageRequest, page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public FacilityDto getFacility(String facilityId) {
        return FacilityMapper.toDto(loadFacility(facilityId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacilitySummaryDto> listFacilities() {
        return facilityRepository.findAll(Sort.by(Sort.Direction.ASC, "facilityName")).stream()
                .map(FacilityMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacilityDto createFacility(FacilityDto dto) {
        if (!StringUtils.hasText(dto.getFacilityName())) {
            throw new IllegalArgumentException("Facility name is required");
        }
        if (!StringUtils.hasText(dto.getFacilityTypeId())) {
            throw new IllegalArgumentException("Facility type is required");
        }
        if (!facilityTypeRepository.existsById(dto.getFacilityTypeId())) {
            throw new IllegalArgumentException("Unknown facility type: " + dto.getFacilityTypeId());
        }

        String facilityId = StringUtils.hasText(dto.getFacilityId())
                ? dto.getFacilityId().trim()
                : FacilityIdGenerator.nextFacilityId();
        if (facilityRepository.existsById(facilityId)) {
            throw new IllegalArgumentException("Facility already exists: " + facilityId);
        }

        Facility entity = new Facility();
        entity.setFacilityId(facilityId);
        FacilityMapper.applyDtoToEntity(dto, entity);
        if (entity.getOpenedDate() == null) {
            entity.setOpenedDate(LocalDateTime.now());
        }
        if (!StringUtils.hasText(entity.getDefaultInventoryItemTypeId())) {
            entity.setDefaultInventoryItemTypeId("NON_SERIAL_INV_ITEM");
        }
        return FacilityMapper.toDto(facilityRepository.save(entity));
    }

    @Override
    public FacilityDto updateFacility(String facilityId, FacilityDto dto) {
        if (!StringUtils.hasText(dto.getFacilityName())) {
            throw new IllegalArgumentException("Facility name is required");
        }
        if (StringUtils.hasText(dto.getFacilityTypeId())
                && !facilityTypeRepository.existsById(dto.getFacilityTypeId())) {
            throw new IllegalArgumentException("Unknown facility type: " + dto.getFacilityTypeId());
        }
        Facility entity = loadFacility(facilityId);
        FacilityMapper.applyDtoToEntity(dto, entity);
        return FacilityMapper.toDto(facilityRepository.save(entity));
    }

    private Facility loadFacility(String facilityId) {
        return facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found: " + facilityId));
    }

    private Specification<Facility> buildSpecification(FacilitySearchRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(request.getFacilityId())) {
                predicates.add(matchText(cb, root.get("facilityId"), request.getFacilityId(), request.getFacilityIdMatchMode()));
            }
            if (StringUtils.hasText(request.getFacilityName())) {
                predicates.add(matchText(cb, root.get("facilityName"), request.getFacilityName(), request.getFacilityNameMatchMode()));
            }
            if (StringUtils.hasText(request.getFacilityTypeId())) {
                predicates.add(cb.equal(root.get("facilityTypeId"), request.getFacilityTypeId()));
            }
            if (StringUtils.hasText(request.getProductStoreId())) {
                predicates.add(cb.equal(root.get("productStoreId"), request.getProductStoreId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Predicate matchText(javax.persistence.criteria.CriteriaBuilder cb,
                                javax.persistence.criteria.Expression<String> field,
                                String value,
                                TextMatchMode mode) {
        String pattern;
        switch (mode) {
            case EQUALS:
                return cb.equal(cb.lower(field), value.toLowerCase());
            case STARTS_WITH:
                pattern = value.toLowerCase() + "%";
                break;
            case ENDS_WITH:
                pattern = "%" + value.toLowerCase();
                break;
            case INCLUDE:
            case CONTAINS:
            default:
                pattern = "%" + value.toLowerCase() + "%";
                break;
        }
        return cb.like(cb.lower(field), pattern);
    }
}

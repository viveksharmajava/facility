package com.playpro.playpro.facility.controller;

import com.playpro.playpro.facility.entity.FacilityType;
import com.playpro.playpro.facility.repository.FacilityTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/facility/reference")
public class ReferenceController {

    private final FacilityTypeRepository facilityTypeRepository;

    public ReferenceController(FacilityTypeRepository facilityTypeRepository) {
        this.facilityTypeRepository = facilityTypeRepository;
    }

    @GetMapping("/facility-types")
    public List<ReferenceItem> facilityTypes() {
        return facilityTypeRepository.findAll().stream()
                .map(this::toItem)
                .collect(Collectors.toList());
    }

    private ReferenceItem toItem(FacilityType type) {
        ReferenceItem item = new ReferenceItem();
        item.setId(type.getFacilityTypeId());
        item.setLabel(type.getDescription() != null ? type.getDescription() : type.getFacilityTypeId());
        return item;
    }

    public static class ReferenceItem {
        private String id;
        private String label;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}

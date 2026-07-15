package com.playpro.playpro.facility.dto;

import com.playpro.playpro.facility.search.TextMatchMode;

public class FacilitySearchRequest {

    private String facilityId;
    private String facilityIdMatch = "CONTAINS";
    private String facilityName;
    private String facilityNameMatch = "CONTAINS";
    private String facilityTypeId;
    private String productStoreId;
    private int page = 0;
    private int size = 20;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityIdMatch() {
        return facilityIdMatch;
    }

    public void setFacilityIdMatch(String facilityIdMatch) {
        this.facilityIdMatch = facilityIdMatch;
    }

    public TextMatchMode getFacilityIdMatchMode() {
        return TextMatchMode.fromString(facilityIdMatch);
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityNameMatch() {
        return facilityNameMatch;
    }

    public void setFacilityNameMatch(String facilityNameMatch) {
        this.facilityNameMatch = facilityNameMatch;
    }

    public TextMatchMode getFacilityNameMatchMode() {
        return TextMatchMode.fromString(facilityNameMatch);
    }

    public String getFacilityTypeId() {
        return facilityTypeId;
    }

    public void setFacilityTypeId(String facilityTypeId) {
        this.facilityTypeId = facilityTypeId;
    }

    public String getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(String productStoreId) {
        this.productStoreId = productStoreId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

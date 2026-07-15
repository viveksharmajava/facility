package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class ProductStoreFacilityId implements Serializable {

    @Column(name = "product_store_id", length = 20)
    private String productStoreId;

    @Column(name = "facility_id", length = 20)
    private String facilityId;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    public ProductStoreFacilityId() {
    }

    public ProductStoreFacilityId(String productStoreId, String facilityId, LocalDateTime fromDate) {
        this.productStoreId = productStoreId;
        this.facilityId = facilityId;
        this.fromDate = fromDate;
    }

    public String getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(String productStoreId) {
        this.productStoreId = productStoreId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStoreFacilityId)) return false;
        ProductStoreFacilityId that = (ProductStoreFacilityId) o;
        return Objects.equals(productStoreId, that.productStoreId)
                && Objects.equals(facilityId, that.facilityId)
                && Objects.equals(fromDate, that.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productStoreId, facilityId, fromDate);
    }
}

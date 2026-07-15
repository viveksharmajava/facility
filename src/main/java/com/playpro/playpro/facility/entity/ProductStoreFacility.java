package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_store_facility")
public class ProductStoreFacility {

    @EmbeddedId
    private ProductStoreFacilityId id;

    @Column(name = "thru_date")
    private LocalDateTime thruDate;

    @Column(name = "sequence_num")
    private Long sequenceNum;

    public ProductStoreFacilityId getId() {
        return id;
    }

    public void setId(ProductStoreFacilityId id) {
        this.id = id;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }

    public Long getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(Long sequenceNum) {
        this.sequenceNum = sequenceNum;
    }
}

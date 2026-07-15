package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "facility_location")
@IdClass(FacilityLocation.FacilityLocationId.class)
public class FacilityLocation {

    @Id
    @Column(name = "facility_id", length = 20)
    private String facilityId;

    @Id
    @Column(name = "location_seq_id", length = 20)
    private String locationSeqId;

    @Column(name = "location_type_enum_id", length = 20)
    private String locationTypeEnumId;

    @Column(name = "area_id", length = 20)
    private String areaId;

    @Column(name = "aisle_id", length = 20)
    private String aisleId;

    @Column(name = "section_id", length = 20)
    private String sectionId;

    @Column(name = "level_id", length = 20)
    private String levelId;

    @Column(name = "position_id", length = 20)
    private String positionId;

    @Column(name = "geo_point_id", length = 20)
    private String geoPointId;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getLocationSeqId() {
        return locationSeqId;
    }

    public void setLocationSeqId(String locationSeqId) {
        this.locationSeqId = locationSeqId;
    }

    public String getLocationTypeEnumId() {
        return locationTypeEnumId;
    }

    public void setLocationTypeEnumId(String locationTypeEnumId) {
        this.locationTypeEnumId = locationTypeEnumId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAisleId() {
        return aisleId;
    }

    public void setAisleId(String aisleId) {
        this.aisleId = aisleId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getGeoPointId() {
        return geoPointId;
    }

    public void setGeoPointId(String geoPointId) {
        this.geoPointId = geoPointId;
    }

    public static class FacilityLocationId implements Serializable {

        private String facilityId;
        private String locationSeqId;

        public FacilityLocationId() {
        }

        public FacilityLocationId(String facilityId, String locationSeqId) {
            this.facilityId = facilityId;
            this.locationSeqId = locationSeqId;
        }

        public String getFacilityId() {
            return facilityId;
        }

        public void setFacilityId(String facilityId) {
            this.facilityId = facilityId;
        }

        public String getLocationSeqId() {
            return locationSeqId;
        }

        public void setLocationSeqId(String locationSeqId) {
            this.locationSeqId = locationSeqId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FacilityLocationId)) return false;
            FacilityLocationId that = (FacilityLocationId) o;
            return Objects.equals(facilityId, that.facilityId)
                    && Objects.equals(locationSeqId, that.locationSeqId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(facilityId, locationSeqId);
        }
    }
}

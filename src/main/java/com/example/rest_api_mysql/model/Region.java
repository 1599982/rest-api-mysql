package com.example.rest_api_mysql.model;

import jakarta.persistence.*;

@Entity
public class Region {
    @Id
    private Integer regionId;
    @Column(name = "regiondescription")
    private String regionDescription;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }
}

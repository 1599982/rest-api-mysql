package com.example.rest_api_mysql.service;

import com.example.rest_api_mysql.model.Region;
import com.example.rest_api_mysql.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public ResponseEntity<Region> createRegion(Region region) {
        return new ResponseEntity<>(regionRepository.save(region), HttpStatus.CREATED);
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public ResponseEntity<Region> updateRegion(Integer regionId, Region region) {
        Region find_region = regionRepository.findById(regionId).orElseThrow();
        find_region.setRegionDescription(region.getRegionDescription());

        return new ResponseEntity<>(regionRepository.save(find_region), HttpStatus.OK);
    }

    public ResponseEntity<Region> deleteRegion(Integer regionId) {
        Region find_region = regionRepository.findById(regionId).orElseThrow();
        regionRepository.deleteById(regionId);
        return new ResponseEntity<>(find_region, HttpStatus.OK);
    }

}

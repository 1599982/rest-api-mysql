package com.example.rest_api_mysql.controller;

import com.example.rest_api_mysql.model.Region;
import com.example.rest_api_mysql.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/region")
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        return regionService.createRegion(region);
    }

    @GetMapping("/regions")
    public @ResponseBody List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @PutMapping("/region/{regionId}")
    public ResponseEntity<Region> updateRegion(@PathVariable("regionId") Integer regionId, @RequestBody Region region) {
        return regionService.updateRegion(regionId, region);
    }

    @DeleteMapping("/region/{regionId}")
    public ResponseEntity<Region> deleteRegion(@PathVariable("regionId") Integer regionId) {
        return regionService.deleteRegion(regionId);
    }
}

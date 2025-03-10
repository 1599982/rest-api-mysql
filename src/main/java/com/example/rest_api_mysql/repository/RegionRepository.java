package com.example.rest_api_mysql.repository;

import com.example.rest_api_mysql.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {}

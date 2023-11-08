package com.example.LaptopShop.repositories;

import com.example.LaptopShop.models.Brand;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BrandRepository extends Neo4jRepository<Brand,Long> {
}

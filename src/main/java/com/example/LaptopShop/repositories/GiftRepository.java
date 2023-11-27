package com.example.LaptopShop.repositories;

import com.example.LaptopShop.models.Gift;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GiftRepository extends Neo4jRepository<Gift,Long> {
}

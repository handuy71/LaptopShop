package com.example.LaptopShop.repositories.components;

import com.example.LaptopShop.models.components.Battery;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BatteryRepository extends Neo4jRepository<Battery,Long> {
}

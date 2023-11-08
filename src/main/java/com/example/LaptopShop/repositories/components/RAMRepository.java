package com.example.LaptopShop.repositories.components;

import com.example.LaptopShop.models.components.RAM;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RAMRepository extends Neo4jRepository<RAM,Long> {
}

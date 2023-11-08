package com.example.LaptopShop.repositories.components;

import com.example.LaptopShop.models.components.OS;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OSRepository extends Neo4jRepository<OS,Long> {
}

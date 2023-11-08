package com.example.LaptopShop.repositories.components;

import com.example.LaptopShop.models.components.Disk;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DiskRepository extends Neo4jRepository<Disk,Long> {
}

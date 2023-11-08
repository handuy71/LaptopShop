package com.example.LaptopShop.repositories.components;

import com.example.LaptopShop.models.components.CPU;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CPURepository extends Neo4jRepository<CPU,Long>
{
}

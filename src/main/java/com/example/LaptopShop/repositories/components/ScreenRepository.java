package com.example.LaptopShop.repositories.components;

import com.example.LaptopShop.models.components.Screen;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ScreenRepository extends Neo4jRepository<Screen,Long> {
}

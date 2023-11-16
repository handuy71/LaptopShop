package com.example.LaptopShop.repositories;

import com.example.LaptopShop.models.Order;
import com.example.LaptopShop.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface OrderRepository extends Neo4jRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
}

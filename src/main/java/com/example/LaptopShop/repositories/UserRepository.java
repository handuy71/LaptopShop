package com.example.LaptopShop.repositories;

import com.example.LaptopShop.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    @Query("MATCH (u:User) RETURN DISTINCT u")
    List<User> getUsers();

}


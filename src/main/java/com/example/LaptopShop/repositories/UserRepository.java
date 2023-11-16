package com.example.LaptopShop.repositories;

import com.example.LaptopShop.models.CartProduct;
import com.example.LaptopShop.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Query("MATCH (u:User) RETURN DISTINCT u")
    List<User> getUsers();
    @Query("MATCH (u:User) -[r:HAS_IN_CART]-> (p:Product) WHERE id(u) = id($user) RETURN DISTINCT r,p")
    List<CartProduct> findCartProduct(@Param("user") User user);
    @Query("MATCH (u:User)-[r:HAS_IN_CART]->() WHERE id(u)=$userId delete r")
    void clearUserCart(@Param("userId") Long userId);

}


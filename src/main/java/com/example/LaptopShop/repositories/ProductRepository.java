package com.example.LaptopShop.repositories;

import com.example.LaptopShop.DTO.ProductDTO;
import com.example.LaptopShop.models.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository extends Neo4jRepository<Product, Long> {
    //Find product by name containing keyword
    @Query("MATCH (p:Product)-[rBrand:HAS_BRAND]->(b:Brand),\n" +
            "(p)-[rBattery:HAS_BATTERY]->(bt:Battery),\n" +
            "(p)-[rCPU:HAS_CPU]->(c:CPU),\n" +
            "(p)-[rDisk:HAS_DISK]->(d:Disk),\n" +
            "(p)-[rOS:HAS_OS]->(o:OS),\n" +
            "(p)-[rRAM:HAS_RAM]->(r:RAM),\n" +
            "(p)-[rScreen:HAS_SCREEN]->(s:Screen) " +
            "WHERE ($name IS NULL OR p.name CONTAINS $name) " +
            "AND ($minPrice IS NULL OR p.price >= $minPrice) " +
            "AND ($maxPrice IS NULL OR p.price <= $maxPrice) " +
            "AND ($BrandId IS NULL OR id(b) = $BrandId) " +
            "AND ($CPUId IS NULL OR id(c) = $CPUId) " +
            "AND ($RAMId IS NULL OR id(r) = $RAMId) " +
            "AND ($ScreenId IS NULL OR id(s) = $ScreenId) " +
            "AND ($DiskId IS NULL OR id(d) = $DiskId) " +
            "AND ($BatteryId IS NULL OR id(bt) = $BatteryId) " +
            "AND ($OSId IS NULL OR id(o) = $OSId) " +
            "RETURN DISTINCT p,rBrand,rBattery,rCPU,rDisk,rOS,rRAM,rScreen,b,bt,c,d,o,r,s")
    List<Product> findProductsByFilter(@Param("name") String name,
                                       @Param("minPrice") Long minPrice,
                                       @Param("maxPrice") Long maxPrice,
                                       @Param("BrandId") Long BrandId,
                                       @Param("CPUId") Long CPUId,
                                       @Param("RAMId") Long RAMId,
                                       @Param("ScreenId") Long ScreenId,
                                       @Param("DiskId") Long DiskId,
                                       @Param("BatteryId") Long BatteryId,
                                       @Param("OSId") Long OSId);

    @Query("MATCH (p1:Product)-[r:HAS_BRAND|HAS_CPU|HAS_RAM|HAS_SCREEN|HAS_DISK|HAS_BATTERY|HAS_OS]-(component)<-[:HAS_BRAND|HAS_CPU|HAS_RAM|HAS_SCREEN|HAS_DISK|HAS_BATTERY|HAS_OS]-(p2:Product) " +
            "WHERE id(p1) = $productId " +
            "AND id(p1) <> id(p2) " +
            "WITH p2, r, component, COUNT(DISTINCT component) AS commonComponents " +
            "ORDER BY commonComponents DESC " +
            "LIMIT 4 " +
            "WITH p2, COLLECT(r) AS relationships, COLLECT(component) AS relatedNodes " +
            "MATCH (relatedNode)-[relatedTo:HAS_BRAND|HAS_CPU|HAS_RAM|HAS_SCREEN|HAS_DISK|HAS_BATTERY|HAS_OS]-(p2) " +
            "RETURN p2, relationships, relatedNodes, COLLECT(relatedNode) AS allRelatedNodes, COLLECT(relatedTo) AS allRelationships")
    List<Product> findRelatedProducts(@Param("productId") Long productId);
    Product getById(Long id);

}

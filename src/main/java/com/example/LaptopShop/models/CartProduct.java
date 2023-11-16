package com.example.LaptopShop.models;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class CartProduct {
    @RelationshipId @GeneratedValue
    private Long id;
    private int quantity;

    @TargetNode
    private Product product;

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

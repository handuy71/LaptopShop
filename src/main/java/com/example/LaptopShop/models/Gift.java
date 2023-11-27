package com.example.LaptopShop.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

public class Gift {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int inventory;

    public Gift(Long id, String name, int inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}

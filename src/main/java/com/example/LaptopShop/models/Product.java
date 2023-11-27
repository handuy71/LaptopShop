package com.example.LaptopShop.models;

import com.example.LaptopShop.models.components.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Node
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Long price;
    private int warranty;
    private String description;
    private int inventory;
    @Relationship(type = "HAS_BRAND", direction = Relationship.Direction.OUTGOING)
    private Brand brand;

    @Relationship(type = "HAS_CPU", direction = Relationship.Direction.OUTGOING)
    private CPU cpu;

    @Relationship(type = "HAS_RAM", direction = Relationship.Direction.OUTGOING)
    private RAM ram;

    @Relationship(type = "HAS_SCREEN", direction = Relationship.Direction.OUTGOING)
    private Screen screen;

    @Relationship(type = "HAS_DISK", direction = Relationship.Direction.OUTGOING)
    private Disk disk;

    @Relationship(type = "HAS_BATTERY", direction = Relationship.Direction.OUTGOING)
    private Battery battery;

    @Relationship(type = "HAS_OS", direction = Relationship.Direction.OUTGOING)
    private OS os;
    @Relationship(type = "HAS_GIFTS", direction = Relationship.Direction.OUTGOING)
    private List<Gift> gifts;

    public Product() {
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public List<Gift> getGifts() {
        return gifts.stream()
                .filter(gift -> gift.getInventory() > 0)
                .collect(Collectors.toList());
    }

    public List<String> getGiftNames() {
        return gifts.stream()
                .filter(gift -> gift.getInventory() > 0)
                .map(Gift::getName)
                .collect(Collectors.toList());
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }
}

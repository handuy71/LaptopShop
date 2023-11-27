package com.example.LaptopShop.DTO;

import java.util.List;

public class CartProductDTO {
    private Long id;
    private Long productId;
    private String productName;
    private Long productPrice;
    private int warranty;
    private int quantity;
    private List<String> gifts;

    public List<String> getGifts() {
        return gifts;
    }

    public void setGifts(List<String> gifts) {
        this.gifts = gifts;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }
}

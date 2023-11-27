package com.example.LaptopShop.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Node
public class Order {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date shipDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date deliveryDate;
    private Long totalPrice;
    private String status;
    @Relationship(type = "INCLUDES", direction = Relationship.Direction.OUTGOING)
    private List<OrderProduct> orderProducts;
    @Relationship(type = "PLACED", direction = Relationship.Direction.INCOMING)
    private User user;

    public Order(Long id, String name, String phone, String address,
                 Date createDate, Date shipDate, Date deliveryDate, Long totalPrice,
                 String status, List<OrderProduct> orderProducts, User user) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.shipDate = shipDate;
        this.deliveryDate = deliveryDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderProducts = orderProducts;
        this.user = user;
    }

    public Order() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}

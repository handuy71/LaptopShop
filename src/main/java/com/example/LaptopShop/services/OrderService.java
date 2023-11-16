package com.example.LaptopShop.services;

import com.example.LaptopShop.models.CartProduct;
import com.example.LaptopShop.models.Order;
import com.example.LaptopShop.models.OrderProduct;
import com.example.LaptopShop.models.User;
import com.example.LaptopShop.repositories.OrderRepository;
import com.example.LaptopShop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
    public Order createOrder(User user,String name,String phone,String address){
        Order order = new Order();
        order.setUser(user);
        order.setName(name);
        order.setPhone(phone);
        order.setAddress(address);
        order.setCreateDate(new Date());
        order.setShipDate(new Date());
        order.setDeliveryDate(new Date());
        order.setTotalPrice(calculateTotalPrice(user.getCartProducts()));
        order.setStatus("Waiting confirm");
        List<OrderProduct> orderProducts = convertCartProductsToOrderProducts(user.getCartProducts());
        order.setOrderProducts(orderProducts);
        userRepository.clearUserCart(user.getId());
        return orderRepository.save(order);
    }
    public List<Order> getAllOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    private List<OrderProduct> convertCartProductsToOrderProducts(List<CartProduct> cartProducts) {
        return cartProducts.stream()
                .map(cartProduct -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setQuantity(cartProduct.getQuantity());
                    orderProduct.setProduct(cartProduct.getProduct());
                    orderProduct.setPrice(cartProduct.getProduct().getPrice());
                    return orderProduct;
                })
                .collect(Collectors.toList());
    }
    private Long calculateTotalPrice(List<CartProduct> cartProducts) {
        return cartProducts.stream()
                .mapToLong(cartProduct -> cartProduct.getProduct().getPrice() * cartProduct.getQuantity())
                .sum();
    }
}

package com.example.LaptopShop.api;

import com.example.LaptopShop.DTO.ProductDTO;
import com.example.LaptopShop.models.Order;
import com.example.LaptopShop.models.User;
import com.example.LaptopShop.services.ComponentService;
import com.example.LaptopShop.services.OrderService;
import com.example.LaptopShop.services.ProductService;
import com.example.LaptopShop.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@SessionAttributes("loggedInUser")
@RequestMapping("/api")
public class TestAPI {
    private final ProductService productService;
    private final ComponentService componentService;
    private final UserService userService;
    private final OrderService orderService;

    public TestAPI(ProductService productService, ComponentService componentService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.componentService = componentService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @ModelAttribute("loggedInUser")
    public User loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(auth.getName());
    }

    public User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

        @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> productDetail(@PathVariable Long id){
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }
    @GetMapping("/customer")
    public String customer() {
        return "Hello Customer";
    }

    @GetMapping("/admin")
    public Collection<? extends GrantedAuthority> admin(HttpServletRequest res) {
        User currentUser = getSessionUser(res);
        return currentUser.getAuthorities();
    }

    @GetMapping("/")
    public ResponseEntity<Page<ProductDTO>> searchLaptops(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) Long BrandId,
            @RequestParam(required = false) Long CPUId,
            @RequestParam(required = false) Long RAMId,
            @RequestParam(required = false) Long ScreenId,
            @RequestParam(required = false) Long DiskId,
            @RequestParam(required = false) Long BatteryId,
            @RequestParam(required = false) Long OSId
    ) {
        Page<ProductDTO> productDTOPage = productService.findProductsByFilter(page, size, name, minPrice, maxPrice, BrandId, CPUId, RAMId, ScreenId, DiskId, BatteryId, OSId);
        return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
    }

    //    @GetMapping("/recommend")
//    public ResponseEntity<List<ProductDTO>> searchLaptops(
//            @RequestParam(required = true) Long Id
//    ) {
//        List<ProductDTO> productDTOPage = productService.findRelatedProducts(Id);
//        return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
//    }
    @GetMapping("/order/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService.getAllOrdersByUser(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}

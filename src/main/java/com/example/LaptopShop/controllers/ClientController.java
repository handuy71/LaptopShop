package com.example.LaptopShop.controllers;

import com.example.LaptopShop.DTO.ProductDTO;
import com.example.LaptopShop.models.Brand;
import com.example.LaptopShop.models.Order;
import com.example.LaptopShop.models.User;
import com.example.LaptopShop.models.components.*;
import com.example.LaptopShop.services.ComponentService;
import com.example.LaptopShop.services.OrderService;
import com.example.LaptopShop.services.ProductService;
import com.example.LaptopShop.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class ClientController {
    private final ProductService productService;
    private final ComponentService componentService;
    private final UserService userService;
    private final OrderService orderService;

    public ClientController(ProductService productService, ComponentService componentService, UserService userService, OrderService orderService) {
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

//    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    @GetMapping("/product")
    public String productIndex(
            @RequestParam(defaultValue = "1") int page,
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
            @RequestParam(required = false) Long OSId,
            Model model) {
        Page<ProductDTO> productsPage = productService.findProductsByFilter(page-1, size, name, minPrice, maxPrice, BrandId, CPUId, RAMId, ScreenId, DiskId, BatteryId, OSId);
        List<Brand> brandList=componentService.getAllBrand();
        List<CPU> cpuList=componentService.getAllCPU();
        List<RAM> ramList=componentService.getAllRAM();
        List<Screen> screenList=componentService.getAllScreen();
        List<Disk> diskList=componentService.getAllDisk();
        List<Battery> batteryList=componentService.getAllBattery();
        List<OS> osList=componentService.getAllOS();

        model.addAttribute("products", productsPage);
        model.addAttribute("brands", brandList);
        model.addAttribute("cpus", cpuList);
        model.addAttribute("rams", ramList);
        model.addAttribute("screens", screenList);
        model.addAttribute("disks", diskList);
        model.addAttribute("batteries", batteryList);
        model.addAttribute("oss", osList);
        return "client/home";
    }
    @GetMapping("/product={id}")
    public String productDetail(@PathVariable Long id,Model model){
        ProductDTO productDTO = productService.getProductById(id);
        List<ProductDTO> recommendList = productService.findRelatedProducts(id);
        model.addAttribute("product", productDTO);
        model.addAttribute("recommends",recommendList);
        return "client/detailsp";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "client/login";
    }
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "client/registration";
    }
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "client/registration";
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/account")
    public String showAccount(Model model,HttpServletRequest request) {
        User user = getSessionUser(request);
        List<Order> orders=orderService.getAllOrdersByUser(user.getId());
        model.addAttribute("orders", orders);
        model.addAttribute("name", user.getName());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("email", user.getEmail());
        return "client/account";
    }
}
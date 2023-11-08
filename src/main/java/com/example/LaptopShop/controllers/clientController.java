package com.example.LaptopShop.controllers;

import com.example.LaptopShop.DTO.ProductDTO;
import com.example.LaptopShop.models.Brand;
import com.example.LaptopShop.models.components.*;
import com.example.LaptopShop.services.ComponentService;
import com.example.LaptopShop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/jsp")
public class clientController {
    private final ProductService productService;
    private final ComponentService componentService;

    public clientController(ProductService productService, ComponentService componentService) {
        this.productService = productService;
        this.componentService = componentService;
    }

    @GetMapping("/product")
    public String productIndex(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "16") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
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
    @GetMapping("/sp={id}")
    public String productDetail(@PathVariable Long id,Model model){
        ProductDTO productDTO = productService.getProductById(id);
        List<ProductDTO> recommendList = productService.findRelatedProducts(id);
        model.addAttribute("product", productDTO);
        model.addAttribute("recommends",recommendList);
        return "client/detailsp";
    }
}
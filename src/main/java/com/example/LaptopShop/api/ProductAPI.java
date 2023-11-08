package com.example.LaptopShop.api;

import com.example.LaptopShop.DTO.ProductDTO;
import com.example.LaptopShop.models.Brand;
import com.example.LaptopShop.services.ComponentService;
import com.example.LaptopShop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductAPI {
    private final ProductService productService;
    private final ComponentService componentService;
    public ProductAPI(ProductService productService, ComponentService componentService) {
        this.productService = productService;
        this.componentService = componentService;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDTO> productDetail(@PathVariable Long id){
//        ProductDTO productDTO = productService.getProductById(id);
//        return new ResponseEntity<>(productDTO,HttpStatus.OK);
//    }
    @GetMapping("/")
    public ResponseEntity<Page<ProductDTO>> searchLaptops(
            @RequestParam(defaultValue = "0") int page,
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

}

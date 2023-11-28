package com.example.LaptopShop.services;

import com.example.LaptopShop.DTO.ProductDTO;
import com.example.LaptopShop.models.Product;
import com.example.LaptopShop.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<ProductDTO> getAllProductsPaginated(int page, int size) {
        Sort sort = Sort.by(Sort.Order.desc("id"));

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Product> productsPage = productRepository.findAll(pageRequest);

        return productsPage.map(product -> convertToDTO(product));
    }
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        return (ProductDTO) convertToDTO(product);
    }
    public Page<ProductDTO> findProductsByFilter(int page, int size, String name, Long minPrice, Long maxPrice, Long BrandId, Long CPUId, Long RAMId, Long ScreenId, Long DiskId, Long BatteryId, Long OSId) {
        List<Product> products = productRepository.findProductsByFilter(name, minPrice, maxPrice, BrandId, CPUId, RAMId, ScreenId, DiskId, BatteryId, OSId);

        products.sort(Comparator.comparing(Product::getId).reversed());

        int totalProducts = products.size();

        int totalPages = (int) Math.ceil((double) totalProducts / size);

        int start = page * size;
        int end = Math.min(start + size, totalProducts);

        if (start >= totalProducts) {
            return Page.empty();
        }

        List<ProductDTO> productDTOs = new ArrayList<>();

        for (int i = start; i < end; i++) {
            productDTOs.add(convertToDTO(products.get(i)));
        }

        return new PageImpl<>(productDTOs, PageRequest.of(page, size), totalProducts);
    }

    public List<ProductDTO> findRelatedProducts(Long id) {
        List<Product> products = productRepository.findRelatedProducts(id);

        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(convertToDTO(product));
        }
        return productDTOs;
    }

    public ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setWarranty(product.getWarranty());
        productDTO.setInventory(product.getInventory());
        productDTO.setDescription(product.getDescription());

        productDTO.setBrand(product.getBrand());
        productDTO.setCpu(product.getCpu());
        productDTO.setRam(product.getRam());
        productDTO.setScreen(product.getScreen());
        productDTO.setDisk(product.getDisk());
        productDTO.setBattery(product.getBattery());
        productDTO.setOs(product.getOs());
        productDTO.setGifts(product.getGifts());

        return productDTO;
    }
}

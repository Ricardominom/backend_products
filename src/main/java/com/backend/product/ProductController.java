package com.backend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(String name) {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id")Long productId){
        return productService.getProduct(productId);
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productService.createProduct(product);

    }

    @PutMapping("{id}")
    public void updateProduct(@PathVariable("id") Long productId, @RequestBody Product productUpdated) {
        productService.updateProduct(productId, productUpdated);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> updateAvailability(@PathVariable("id") Long productId) {
        boolean newState =  productService.updateAvailability(productId);
        return ResponseEntity.ok("New State " + newState);
    }
}


package com.backend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con ese id"));
    }

    public void createProduct(Product product) {
        productRepository.save(product).getId();
    }

    public void updateProduct(Long productId, Product productUpdated) {
        //Validacion si producto con ese id existe
        Product productExists = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Producto con ese id no existe"));

        //Update Product
        productExists.setName(productUpdated.getName());
        productExists.setPrice(productUpdated.getPrice());
        productExists.setAvailability(productUpdated.isAvailability());

        productRepository.save(productExists);
    }

    public void deleteProduct(Long productId) {
        //Validacion si producto existe
        boolean productoExiste = productRepository.existsById(productId);

        if (!productoExiste){
            throw new NoSuchElementException("El producto no existe");
        }

        productRepository.deleteById(productId);
    }

    public boolean updateAvailability(Long productId) {
        //Validacion si producto con ese id existe
        Product productExists = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Producto con ese id no existe"));

        //Update ProductAvailability
        productExists.setAvailability(!productExists.isAvailability());
        productRepository.save(productExists);
        return productExists.isAvailability();
    }
}

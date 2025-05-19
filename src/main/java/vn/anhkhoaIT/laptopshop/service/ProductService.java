package vn.anhkhoaIT.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.anhkhoaIT.laptopshop.domain.Product;
import vn.anhkhoaIT.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //Save new product to the database
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    //Find all products
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    //Find a product by ID
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    //Delete a product by ID
    public void deleteProductById(Long id) {
        this.productRepository.deleteById(id);
    }

    
}

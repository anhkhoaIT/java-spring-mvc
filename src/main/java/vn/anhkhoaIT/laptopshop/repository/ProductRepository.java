package vn.anhkhoaIT.laptopshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.anhkhoaIT.laptopshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product); // Save a product to the database

    Optional<Product> findById(Long id); // Find a product by ID
    void deleteById(Long id); // Delete a product by ID
}

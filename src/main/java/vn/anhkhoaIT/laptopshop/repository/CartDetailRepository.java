package vn.anhkhoaIT.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.anhkhoaIT.laptopshop.domain.Cart;
import vn.anhkhoaIT.laptopshop.domain.CartDetail;
import vn.anhkhoaIT.laptopshop.domain.Product;



public interface CartDetailRepository extends JpaRepository<CartDetail, Long>{
    public CartDetail findByCartAndProduct(Cart cart, Product product); // Find CartDetail by Cart and Product
}

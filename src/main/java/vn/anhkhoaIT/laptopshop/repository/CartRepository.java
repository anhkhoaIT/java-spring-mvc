package vn.anhkhoaIT.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.anhkhoaIT.laptopshop.domain.Cart;
import vn.anhkhoaIT.laptopshop.domain.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
    public Cart findByUser(User user); 
}

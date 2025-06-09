package vn.anhkhoaIT.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.anhkhoaIT.laptopshop.domain.CartDetail;



public interface CartDetailRepository extends JpaRepository<CartDetail, Long>{
    
}

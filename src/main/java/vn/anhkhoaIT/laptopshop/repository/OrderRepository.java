package vn.anhkhoaIT.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.anhkhoaIT.laptopshop.domain.Order;
import vn.anhkhoaIT.laptopshop.domain.User;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    Order findByUser(User user);
}

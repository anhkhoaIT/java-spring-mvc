package vn.anhkhoaIT.laptopshop.service;

import java.util.List;


import org.springframework.stereotype.Service;

import vn.anhkhoaIT.laptopshop.domain.Cart;
import vn.anhkhoaIT.laptopshop.domain.Order;
import vn.anhkhoaIT.laptopshop.domain.OrderDetail;
import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.repository.OrderDetailRepository;
import vn.anhkhoaIT.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOrderByEmail(String email) {
        User user = this.userService.getUserByEmail(email);
        if(user != null) {
            Order order = this.orderRepository.findByUser(user);
            return order;
        }
        return null;
    }

    public Order getOrderById(Long id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Order order) {
        if (order != null) {
            List<OrderDetail> orderDetails = order.getOrderDetails();
            for (OrderDetail orderDetail : orderDetails) {
                this.orderDetailRepository.deleteById(orderDetail.getId()); // Remove the association with the order
            }
            this.orderRepository.deleteById(order.getId());
        }
    }
}

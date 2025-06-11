package vn.anhkhoaIT.laptopshop.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vn.anhkhoaIT.laptopshop.domain.Cart;
import vn.anhkhoaIT.laptopshop.domain.CartDetail;
import vn.anhkhoaIT.laptopshop.domain.Order;
import vn.anhkhoaIT.laptopshop.domain.OrderDetail;
import vn.anhkhoaIT.laptopshop.repository.OrderRepository;
import vn.anhkhoaIT.laptopshop.service.OrderService;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    public OrderController(OrderService orderService, 
                           OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }
    @GetMapping("/admin/order")
    public String getAllOrder(Model model) {
        List<Order> orders = this.orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, HttpSession session, @PathVariable("id") Long id) {
        Order order = this.orderService.getOrderById(id);
        List<OrderDetail> orderDetails = order == null ? new ArrayList<OrderDetail>() : order.getOrderDetails();
        double totalPrice = 0;
        for (OrderDetail orderDetail : orderDetails) {
            totalPrice += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("orderId", id);
        model.addAttribute("totalPrice", totalPrice);
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable("id") Long id, HttpSession session) {
        Order order = this.orderService.getOrderById(id);
        List<OrderDetail> orderDetails = order == null ? new ArrayList<OrderDetail>() : order.getOrderDetails();
        double totalPrice = 0;
        for (OrderDetail orderDetail : orderDetails) {
            totalPrice += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        model.addAttribute("orderId", id);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("order", order);
        return "admin/order/update";
    }

    @PostMapping("/admin/update-order/{id}")
    public String updateOrder(Model model, HttpSession session, @RequestParam("orderStatus") String orderStatus, @PathVariable("id") Long id) {
        Order order = this.orderService.getOrderById(id);
        if (order != null) {
            order.setStatus(orderStatus);
            this.orderRepository.save(order);
        }
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable("id") Long id) {
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("orderId", order.getId());
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete/{id}")
    public String updateOrder(Model model, @PathVariable("id") Long id) {
        Order order = this.orderService.getOrderById(id);
        this.orderService.deleteOrder(order);
        return "redirect:/admin/order";
    }
}

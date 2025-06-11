package vn.anhkhoaIT.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.anhkhoaIT.laptopshop.service.UserService;

@Controller
public class DashboardController {
    private final UserService userService;
    public DashboardController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/admin")
    public String getDashboard(Model model) {
        model.addAttribute("countUser", this.userService.countUsers());
        model.addAttribute("countOrder", this.userService.countOrders());
        model.addAttribute("countProduct", this.userService.countProducts());
        return "admin/dashboard/show";
    }
}

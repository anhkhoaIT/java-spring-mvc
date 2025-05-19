package vn.anhkhoaIT.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.anhkhoaIT.laptopshop.domain.Product;

@Controller
public class ProductController {
    @GetMapping("/admin/product")
    public String getAdminProduct() {
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }


}

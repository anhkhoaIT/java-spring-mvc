package vn.anhkhoaIT.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import vn.anhkhoaIT.laptopshop.domain.Product;
import vn.anhkhoaIT.laptopshop.service.ProductService;


@Controller
public class ItemController {
    private final ProductService productService;
    public ItemController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/product/{id}")
    public String getProductDetail(Model model,@PathVariable ("id") Long id) { 
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
       return "/client/product/detail";
    }
    
}

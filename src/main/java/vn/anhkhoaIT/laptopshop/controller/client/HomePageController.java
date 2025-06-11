package vn.anhkhoaIT.laptopshop.controller.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import vn.anhkhoaIT.laptopshop.domain.Product;
import vn.anhkhoaIT.laptopshop.domain.Role;
import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.domain.dto.RegisterDTO;
import vn.anhkhoaIT.laptopshop.service.ProductService;
import vn.anhkhoaIT.laptopshop.service.UserService;



@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.productService = productService;
    }
    @GetMapping("/")
    public String home(Model model) {
        // List<Product> products = this.productService.getAllProducts();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> products = this.productService.getAllProducts(pageable);
        List<Product> productList = products.getContent();
        model.addAttribute("products", productList);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "/client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(Model model, @ModelAttribute("registerDTO") @Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the registration page
            return "/client/auth/register";
        }
        User user = userService.convertRegisterDTOToUser(registerDTO);
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        Role role = userService.getRoleByName("USER");
        user.setRole(role);
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        
        return "client/auth/login";
    }

    @GetMapping("/access-denied")
    public String getDenyPage(Model model) {
        
        return "client/auth/deny";
    }

   @GetMapping("/products")
    public String getProductsPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        if (pageOptional.isPresent()) {
            try {
                page = Integer.parseInt(pageOptional.get());
            } catch (NumberFormatException e) {
                page = 1; // Default to page 1 if parsing fails
            }
        }
        Pageable pageable = PageRequest.of(page - 1, 5);

        Page<Product> products = this.productService.getAllProducts(pageable);
        List<Product> productList = products.getContent();
        model.addAttribute("products", productList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        return "client/product/show";
    }



}

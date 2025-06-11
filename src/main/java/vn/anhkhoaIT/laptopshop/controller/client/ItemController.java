package vn.anhkhoaIT.laptopshop.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.xalan.xsltc.compiler.sym;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import vn.anhkhoaIT.laptopshop.domain.Cart;
import vn.anhkhoaIT.laptopshop.domain.CartDetail;
import vn.anhkhoaIT.laptopshop.domain.Product;
import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.service.ProductService;
import vn.anhkhoaIT.laptopshop.service.UserService;


@Controller
public class ItemController {
    private final ProductService productService;
    private final UserService userService;
    public ItemController(ProductService productService, 
                          UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }
    @GetMapping("/product/{id}")
    public String getProductDetail(Model model,@PathVariable ("id") Long id) { 
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
       return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable("id") Long productId, @SessionAttribute("email") String email, HttpSession session) {
        this.productService.handleAddProductToCart(productId, email, session);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Cart cart = this.productService.getCartByUserEmail(email);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartProduct(@PathVariable("id") Long cartDetailId, HttpSession session) {
        this.productService.handleDeleteCartProduct(cartDetailId, session);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Cart cart = this.productService.getCartByUserEmail(email);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("cart", cart);
        return "client/cart/checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(Model model, @RequestParam("receiverName") String name, @RequestParam("receiverAddress") String address, @RequestParam("receiverPhone") String phone, HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        User user = new User();
        user.setId(userId);
        //in ra 3 trường thông tin name, address, phone
        System.out.println("Receiver Name: " + name);
        System.out.println("Receiver Address: " + address);
        System.out.println("Receiver Phone: " + phone);
        this.productService.handlePlaceOrder(user, name, address, phone, session);
        return "redirect:/thanks";
    }

    @GetMapping("/thanks")
    public String thanksPage(Model model, HttpSession session) {
        return "client/cart/thanks";
    }
}

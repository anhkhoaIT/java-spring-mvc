package vn.anhkhoaIT.laptopshop.service;

import java.util.List;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.anhkhoaIT.laptopshop.domain.Cart;
import vn.anhkhoaIT.laptopshop.domain.CartDetail;
import vn.anhkhoaIT.laptopshop.domain.Product;
import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.repository.CartDetailRepository;
import vn.anhkhoaIT.laptopshop.repository.CartRepository;
import vn.anhkhoaIT.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, 
                          CartRepository cartRepository, 
                          CartDetailRepository cartDetailRepository, UserService userService) {
        this.userService = userService;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }
    //Save new product to the database
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    //Find all products
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    //Find a product by ID
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    //Delete a product by ID
    public void deleteProductById(Long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(long productId, String email, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        
        
        if (user != null) {
            // If the cart does not exist, create a new one
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
            Cart otherCart = new Cart();
            otherCart.setUser(user);
            otherCart.setSum(0);
            cart = this.cartRepository.save(otherCart);
            }
        Product product = this.getProductById(productId);
        // Check if the product is already in the cart
        CartDetail oldCartDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);
        if (oldCartDetail == null) {
            int s = cart.getSum() + 1;
           CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setQuantity(1); // Default quantity is 1
            cartDetail.setPrice(product.getPrice()); // Set the price from the product
            cart.setSum(s); // Increment the cart sum
            this.cartRepository.save(cart); // Save the updated cart
            this.cartDetailRepository.save(cartDetail); 
            session.setAttribute("sum", s);
        } else {
            oldCartDetail.setQuantity(oldCartDetail.getQuantity() + 1); // Increment quantity
            this.cartDetailRepository.save(oldCartDetail); 
        }
        
        
        }
    }

    public Cart getCartByUserEmail(String email) {

        User user = this.userService.getUserByEmail(email);
        if(user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            return cart;
        }
        return null;
    }
    public void handleDeleteCartProduct(Long cartDetailId, HttpSession session) {
        CartDetail cartDetail = this.cartDetailRepository.findById(cartDetailId).get();
        Cart cart = cartDetail.getCart();
        this.cartDetailRepository.delete(cartDetail);
        if (cart.getSum() > 1) {
            int newSum = cart.getSum() - 1;
            cart.setSum(newSum);
            session.setAttribute("sum", newSum);
        } else {
            this.cartRepository.delete(cart); // Delete the cart if it is empty
            session.setAttribute("sum", 0); // Reset the session sum
        }
    }
}

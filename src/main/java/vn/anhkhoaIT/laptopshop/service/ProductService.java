package vn.anhkhoaIT.laptopshop.service;

import java.util.List;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

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

    public void handleAddProductToCart(long productId, String email) {
        User user = this.userService.getUserByEmail(email);
        
        
        if (user != null) {
            // If the cart does not exist, create a new one
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
            Cart otherCart = new Cart();
            otherCart.setUser(user);
            otherCart.setSum(1);
            cart = this.cartRepository.save(otherCart);
        }

        Product product = this.getProductById(productId);
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setQuantity(1); // Default quantity is 1
        cartDetail.setPrice(product.getPrice()); // Set the price from the product
        this.cartDetailRepository.save(cartDetail);
        }
    }

    
}

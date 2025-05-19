package vn.anhkhoaIT.laptopshop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.anhkhoaIT.laptopshop.domain.Product;
import vn.anhkhoaIT.laptopshop.domain.Role;
import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;


@Controller
public class ProductController {
    private final ServletContext servletContext;
    private final ProductService productService;

    
    public ProductController(ServletContext servletContext, ProductService productService) {
        this.productService = productService;
        this.servletContext = servletContext;
    }
    @GetMapping("/admin/product")
    public String getAdminProduct(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postCreateProduct(Model model, @Valid @ModelAttribute("newProduct") Product newProduct,
                    BindingResult newProductBindingResult, @RequestParam("khoaFile") MultipartFile file
    ) {
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (">>>>>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        if(newProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }
        String image = "";
        
        // Upload file
        if (!file.isEmpty()) {
            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            image = filename;
            try{
            byte[] bytes = file.getBytes();
            String rootPath = this.servletContext.getRealPath("/resources/images");

            File dir = new File(rootPath + File.separator + "product");
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            
            File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
        }
        newProduct.setImage(image);
        this.productService.saveProduct(newProduct);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetail(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable Long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("currentProduct", currentProduct);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(Model model, @ModelAttribute("currentProduct") Product currentProduct,
                    @RequestParam("khoaFile") MultipartFile file
    ) {
        Product product = this.productService.getProductById(currentProduct.getId());
        String image = "";
        
        // Upload file
        if (!file.isEmpty()) {
            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            image = filename;
            try{
            byte[] bytes = file.getBytes();
            String rootPath = this.servletContext.getRealPath("/resources/images");

            File dir = new File(rootPath + File.separator + "product");
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            
            File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
        }
        currentProduct.setImage(image);
        if (product != null) {
            product.setName(currentProduct.getName());
            product.setPrice(currentProduct.getPrice());
            product.setDetailDesc(currentProduct.getDetailDesc());
            product.setShortDesc(currentProduct.getShortDesc());
            product.setQuantity(currentProduct.getQuantity());
            product.setSold(currentProduct.getSold());
            product.setFactory(currentProduct.getFactory());
            product.setTarget(currentProduct.getTarget());
            product.setImage(currentProduct.getImage());
        }
        
        this.productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/delete";
    }

     @PostMapping("/admin/product/delete/{id}")
    public String postDeleteUser(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        
        String rootPath = this.servletContext.getRealPath("/resources/images");
        File dir = new File(rootPath + File.separator + "product");
        File file = new File(dir.getAbsolutePath() + File.separator + product.getImage());
        if (file.exists()) {
            file.delete();
        }
        this.productService.deleteProductById(id);
        return "redirect:/admin/product";
    }

    
    
}

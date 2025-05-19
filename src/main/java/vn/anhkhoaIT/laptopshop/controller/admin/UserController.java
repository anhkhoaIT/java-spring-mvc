package vn.anhkhoaIT.laptopshop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;



import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import vn.anhkhoaIT.laptopshop.domain.Role;
import vn.anhkhoaIT.laptopshop.domain.User;

import vn.anhkhoaIT.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController {
    private final UserService userService;
    
    private final ServletContext servletContext;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, ServletContext servletContext,
            PasswordEncoder passwordEncoder) {
        this.servletContext = servletContext;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        // System.out.println(">>>>>>>>>> Checking user list: " + users);
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    //Create user page
    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // User details page
    @GetMapping("/admin/user/{id}")
    public String getUserDetailsPage(Model model, @PathVariable Long id) {
        // System.out.println("Checking user details page with id: " + id);
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    // Update user page
    @GetMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable Long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("currentUser", currentUser);
        return "admin/user/update";
    }

    // Delete user page
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable Long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/delete";
    }

    
    @PostMapping(value = "/admin/user/create")
    public String getUser(Model model, @Valid @ModelAttribute("newUser") User khoaIT,  BindingResult newUserBindingResult,
    @RequestParam("hoidanitFile") MultipartFile file) {
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (">>>>>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        if(newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }
        String avatar = "";
        
        // Upload file
        
        if (!file.isEmpty()) {
            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            avatar = filename;
            try{
            byte[] bytes = file.getBytes();
            String rootPath = this.servletContext.getRealPath("/resources/images");

            File dir = new File(rootPath + File.separator + "avatar");
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
        
         
        //set password
        String hashPassword = this.passwordEncoder.encode(khoaIT.getPassword());
        khoaIT.setPassword(hashPassword);
        //get role from database
        Role role = this.userService.getRoleByName(khoaIT.getRole().getName());
        khoaIT.setRole(role);
        
        khoaIT.setAvatar(avatar);

        this.userService.saveUser(khoaIT);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("currentUser") User currUser) {
        User user = this.userService.getUserById(currUser.getId());
        if (user != null) {
            user.setEmail(currUser.getEmail());
            user.setFullName(currUser.getFullName());
            user.setAddress(currUser.getAddress());
            user.setPhone(currUser.getPhone());
        }
        // Update the user in the database
        this.userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/delete/{id}")
    public String postDeleteUser(Model model, @PathVariable Long id) {
        User user = this.userService.getUserById(id);
        //xóa ảnh trong file dựa trên tên ảnh có trong user.getAvatar()
        String rootPath = this.servletContext.getRealPath("/resources/images");
        File dir = new File(rootPath + File.separator + "avatar");
        File file = new File(dir.getAbsolutePath() + File.separator + user.getAvatar());
        if (file.exists()) {
            file.delete();
        }
        this.userService.deleteUserById(id);
        // Delete the user from the database
        return "redirect:/admin/user";
    }
    

}


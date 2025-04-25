package vn.anhkhoaIT.laptopshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.repository.UserRepository;
import vn.anhkhoaIT.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String getHomePage(Model model) {
        // List<User> users = this.userService.getUserByEmail("khoa1@gmail.com");
        // System.out.println(users);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        // System.out.println(">>>>>>>>>> Checking user list: " + users);
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    //Create user page
    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // User details page
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailsPage(Model model, @PathVariable Long id) {
        // System.out.println("Checking user details page with id: " + id);
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/detail-user";
    }

    // Update user page
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable Long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("currentUser", currentUser);
        return "admin/user/update-user";
    }

    // Delete user page
    @RequestMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable Long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/delete-user";
    }

    
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String getUser(Model model, @ModelAttribute("newUser") User khoaIT) {
        // Save the user to the database
        this.userService.saveUser(khoaIT);
        System.out.println(khoaIT);
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
        this.userService.deleteUserById(id);
        // Delete the user from the database
        return "redirect:/admin/user";
    }
    

}


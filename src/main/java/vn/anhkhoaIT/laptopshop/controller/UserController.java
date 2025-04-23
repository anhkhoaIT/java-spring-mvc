package vn.anhkhoaIT.laptopshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.anhkhoaIT.laptopshop.service.UserService;

@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String getHomePage() {
        return "hello";
    }
}

// @RestController
// public class UserController {
//     //Dependency Injection
//     private UserService userService;
//     public UserController(UserService userService) {
//         this.userService = userService;
//     }
//     @GetMapping("/")
//     public String getHomePage() {
//         return this.userService.handleHello();
//     }
// }

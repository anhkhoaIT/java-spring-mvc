package vn.anhkhoaIT.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String handleHello() {
        return "Hello from UserService!";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    //Save a user to the database
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }
}

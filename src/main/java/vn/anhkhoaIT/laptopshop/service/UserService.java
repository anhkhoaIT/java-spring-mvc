package vn.anhkhoaIT.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.anhkhoaIT.laptopshop.domain.Role;
import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.domain.dto.RegisterDTO;
import vn.anhkhoaIT.laptopshop.repository.RoleRepository;
import vn.anhkhoaIT.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    //Find a user by ID
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    //Delete a user by ID
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    //Convert registerDTO to User
    public User convertRegisterDTOToUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    // Check if email already exists
    public boolean checkEmailExists(String email) {
        return this.userRepository.existsByEmail(email);
    }
}

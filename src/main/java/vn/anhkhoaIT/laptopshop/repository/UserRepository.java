package vn.anhkhoaIT.laptopshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.anhkhoaIT.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user); // Save a user to the database
    List<User> findByEmail(String email); // Find a user by email
    Optional<User> findById(Long id); // Find a user by ID
    void deleteById(Long id); // Delete a user by ID
}

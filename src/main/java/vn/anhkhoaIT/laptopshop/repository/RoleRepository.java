package vn.anhkhoaIT.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.anhkhoaIT.laptopshop.domain.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name); // Find a role by its name
}

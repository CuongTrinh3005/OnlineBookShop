package com.example.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinebookshop.entity.Role;
import com.example.onlinebookshop.entity.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

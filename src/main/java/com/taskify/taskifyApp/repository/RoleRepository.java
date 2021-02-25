package com.taskify.taskifyApp.repository;

import com.taskify.taskifyApp.entity.ERole;
import com.taskify.taskifyApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

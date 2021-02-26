package com.taskify.taskifyApp.repository;

import com.taskify.taskifyApp.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findById(long id);
}

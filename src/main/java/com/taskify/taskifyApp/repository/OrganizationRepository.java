package com.taskify.taskifyApp.repository;

import com.taskify.taskifyApp.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}

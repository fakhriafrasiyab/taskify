package com.taskify.taskifyApp.service;

import com.taskify.taskifyApp.entity.ERole;
import com.taskify.taskifyApp.entity.Organization;
import com.taskify.taskifyApp.entity.Role;
import com.taskify.taskifyApp.entity.User;
import com.taskify.taskifyApp.repository.OrganizationRepository;
import com.taskify.taskifyApp.repository.RoleRepository;
import com.taskify.taskifyApp.repository.UserRepository;
import com.taskify.taskifyApp.security.jwt.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private OrganizationRepository organizationRep;

    AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, OrganizationRepository organizationRep) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.organizationRep = organizationRep;
    }

    public User store(String name, String email, String password, String organizationName, String phoneNumber, String address) {
        User user = new User(name, email,
                passwordEncoder.encode(password));
        Set<Role> roles = new HashSet<>();

        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);

        user.setRoles(roles);
        userRepository.save(user);

        Organization organization = new Organization(organizationName, phoneNumber, address, user);
        organizationRep.save(organization);
        user.setOrganization(organization);
        userRepository.save(user);
        return user;
    }
}

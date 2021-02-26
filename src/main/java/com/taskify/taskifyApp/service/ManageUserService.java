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
import java.util.Optional;
import java.util.Set;

@Service
public class ManageUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private OrganizationRepository organizationRep;

    ManageUserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, OrganizationRepository organizationRep) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.organizationRep = organizationRep;
    }

    public void store(String name, String surname, String email, String adminUserName) {
        Optional<User> adminUser = userRepository.findByEmail(adminUserName);
        if (adminUser.isPresent()) {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("123456789Faxri"));
            Set<Role> roles = new HashSet<>();
            Role adminRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            user.setRoles(roles);
            user.setOrganization(adminUser.get().getOrganization());
            userRepository.save(user);
        }
    }
}

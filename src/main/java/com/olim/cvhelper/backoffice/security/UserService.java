package com.olim.cvhelper.backoffice.security;

import com.olim.cvhelper.backoffice.entity.Role;
import com.olim.cvhelper.backoffice.entity.User;
import com.olim.cvhelper.backoffice.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(Credentials credentials) {
        User admin = new User();
        admin.setName(credentials.getUsername());
        admin.setUsername(credentials.getLogin());
        admin.setPassword(passwordEncoder.encode(credentials.getPassword()));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
    }
}

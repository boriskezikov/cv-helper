package com.olim.cvhelper.backoffice.security;

import com.olim.cvhelper.backoffice.entity.Role;
import com.olim.cvhelper.backoffice.entity.User;
import com.olim.cvhelper.backoffice.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserServiceCreds {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserCredsConfig userCredsConfig;



    @PostConstruct
    public void createUser() {
        User admin = new User();
        admin.setName(userCredsConfig.getUsername1());
        admin.setUsername(userCredsConfig.getLogin1());
        admin.setPassword(passwordEncoder.encode(userCredsConfig.getPassword1()));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        User admin2 = new User();
        admin.setName(userCredsConfig.getUsername2());
        admin.setUsername(userCredsConfig.getLogin2());
        admin.setPassword(passwordEncoder.encode(userCredsConfig.getPassword2()));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin2);

        User admin3 = new User();
        admin.setName(userCredsConfig.getUsername3());
        admin.setUsername(userCredsConfig.getLogin3());
        admin.setPassword(passwordEncoder.encode(userCredsConfig.getPassword3()));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin3);
    }
}

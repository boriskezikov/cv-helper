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
        if (userRepository.count() == 3) {
            return;
        }
        User admin = new User();
        admin.setName(userCredsConfig.getUsername1());
        admin.setUsername(userCredsConfig.getLogin1());
        admin.setPassword(passwordEncoder.encode(userCredsConfig.getPassword1()));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        User admin2 = new User();
        admin2.setName(userCredsConfig.getUsername2());
        admin2.setUsername(userCredsConfig.getLogin2());
        admin2.setPassword(passwordEncoder.encode(userCredsConfig.getPassword2()));
        admin2.setRole(Role.ADMIN);
        userRepository.save(admin2);

        User admin3 = new User();
        admin3.setName(userCredsConfig.getUsername3());
        admin3.setUsername(userCredsConfig.getLogin3());
        admin3.setPassword(passwordEncoder.encode(userCredsConfig.getPassword3()));
        admin3.setRole(Role.ADMIN);
        userRepository.save(admin3);
    }
}

package com.olim.cvhelper.backoffice.service;

import com.olim.cvhelper.backoffice.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User findByName(String name);
}
package com.olim.cvhelper.data.service;

import com.olim.cvhelper.data.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User findByName(String name);
}
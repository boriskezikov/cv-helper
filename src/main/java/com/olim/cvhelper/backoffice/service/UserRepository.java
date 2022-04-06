package com.olim.cvhelper.backoffice.service;

import com.olim.cvhelper.backoffice.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User findByName(String name);

    @Transactional
    @Query(value = "select * from application_users order by random() limit :1", nativeQuery = true)
    User pickRandomUser();
}
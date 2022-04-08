package com.olim.cvhelper.backoffice.service;

import com.olim.cvhelper.backoffice.entity.User;
import com.olim.cvhelper.bot.exception.NoUserRegisteredException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;


    public Optional<User> get(UUID id) {
        return repository.findById(id);
    }

    public User get(String name) {
        return repository.findByName(name);
    }

    public User update(User entity) {
        return repository.save(entity);
    }

    public User pickRandom() {
        return repository.pickRandomUser().orElseThrow(NoUserRegisteredException::new);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<User> loadAll() {
        return repository.findAll();
    }

    public int count() {
        return (int) repository.count();
    }

}

package com.olim.cvhelper.backoffice.service;

import com.olim.cvhelper.backoffice.entity.CvApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CvApplicationService {

    private final CvApplicationRepository repository;

    @Autowired
    public CvApplicationService(CvApplicationRepository repository) {
        this.repository = repository;
    }

    public Optional<CvApplication> get(UUID id) {
        return repository.findById(id);
    }

    public CvApplication update(CvApplication entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Page<CvApplication> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    public List<CvApplication> findAll() {
        return repository.findAll();
    }

}

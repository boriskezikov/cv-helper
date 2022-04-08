package com.olim.cvhelper.backoffice.service;

import com.olim.cvhelper.backoffice.entity.CvApplication;
import com.olim.cvhelper.backoffice.events.NewApplicationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CvApplicationService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final CvApplicationRepository repository;

    public Optional<CvApplication> get(UUID id) {
        return repository.findById(id);
    }

    public CvApplication update(CvApplication entity) {
        var r = repository.save(entity);
        applicationEventPublisher.publishEvent(new NewApplicationEvent(r));
        return r;
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

package com.olim.cvhelper.backoffice.service;

import com.olim.cvhelper.backoffice.entity.CvApplication;
import com.olim.cvhelper.backoffice.entity.CvApplicationStatus;
import com.olim.cvhelper.backoffice.events.ApplicationFinished;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
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

    @Transactional
    public CvApplication update(CvApplication entity) {
        var saved = repository.save(entity);
        if (saved.getStatus().equals(CvApplicationStatus.CLOSED)) {
            applicationEventPublisher.publishEvent(new ApplicationFinished(this, saved.getChatId()));
        }
        return saved;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public void delete(Collection<CvApplication> applications) {
        repository.deleteAll(applications);
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

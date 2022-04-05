package com.olim.cvhelper.data.service;

import com.olim.cvhelper.data.entity.SamplePerson;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, UUID> {

}
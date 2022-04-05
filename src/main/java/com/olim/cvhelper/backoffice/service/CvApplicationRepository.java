package com.olim.cvhelper.backoffice.service;

import com.olim.cvhelper.backoffice.entity.CvApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CvApplicationRepository extends JpaRepository<CvApplication, UUID> {

}
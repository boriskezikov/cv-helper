package com.olim.cvhelper.data.service;

import com.olim.cvhelper.data.entity.CvApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CvApplicationRepository extends JpaRepository<CvApplication, UUID> {

}
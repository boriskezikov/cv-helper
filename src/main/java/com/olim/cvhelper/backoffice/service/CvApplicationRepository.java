package com.olim.cvhelper.backoffice.service;

import com.olim.cvhelper.backoffice.entity.CvApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface CvApplicationRepository extends JpaRepository<CvApplication, UUID> {


//    @Override
//    @Transactional
//    @Query(value = "select * from cv_applications where to_remove=false", nativeQuery = true)
//    List<CvApplication> findAll();

}
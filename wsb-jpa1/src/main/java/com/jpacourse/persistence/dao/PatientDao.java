package com.jpacourse.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.jpacourse.persistence.entity.DrugEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface PatientDao extends Dao<PatientEntity, Long> {
    @Query("SELECT p FROM PatientEntity p WHERE p.name = :name")
    List<PatientEntity> findByName(@Param("name") String name);
    void addVisit(Long patientId, Long doctorId, LocalDateTime visitTime, String description);

    List<PatientEntity> findByLastName(String lastName);

    List<PatientEntity> findByDrug(String Drug);

    List<DrugEntity> findByNameAndPatient(String name, PatientEntity patient);
}




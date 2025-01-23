package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DrugEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;
    @Repository
    public interface PatientDao extends JpaRepository<PatientEntity, Long> {
        List<PatientEntity> findByDrug(String drugName);
    }

    @Test
    @Transactional
    public void testFindByDrugName() {

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Jan");
        patient.setLastName("Kowalski");

        DrugEntity drug = new DrugEntity();
        drug.setName("Ibuprom");

        List<DrugEntity> drugs = new ArrayList<>();
        drugs.add(drug);
        patient.setDrugs(drugs);
        patientDao.save(patient);

        String drugName = "Ibuprom";
        List<PatientEntity> patients = patientDao.findByDrug(drugName);

        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(1);
        assertThat(patients.get(0).getFirstName()).isEqualTo("Jan");
        assertThat(patients.get(0).getLastName()).isEqualTo("Kowalski");


    }

}

package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.Dao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.DrugEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;
    private Dao addressDao;


    @Test
    @Transactional
    public void testFindByDrugName() {

    }

    @Nested
    @DataJpaTest
    class PatientDrugRelationTest {

        @Autowired
        private PatientDao patientDao;

        @Autowired
        private DrugEntity drugDao;

        @Test
        @Transactional
        public void
        testPatientAndDrugsRelation() {
            PatientEntity patient = new PatientEntity();
            patient.setName("Jan Kowalski");
            patientDao.save(patient);

            DrugEntity drug1 = new DrugEntity();
            drug1.setName("Ibum");
            drug1.setPatient(patient);
            drugDao.save(drug1);

            DrugEntity drug2 = new DrugEntity();
            drug2.setName("Ibuprom");
            drug2.setPatient(patient);
            drugDao.save(drug2);

            PatientEntity savedPatient = (PatientEntity) patientDao.findById(patient.getId()).orElse(null);
            List<DrugEntity> drugs = drugDao.findByPatient(patient);

            assertThat(savedPatient).isNotNull();
            assertThat(savedPatient.getName()).isEqualTo("Jan Kowalski");

            assertThat(drugs).isNotNull();
            assertThat(drugs.size()).isEqualTo(2);
            assertThat(drugs.get(0).getName()).isEqualTo("Ibum");
            assertThat(drugs.get(1).getName()).isEqualTo("Ibuprom");
        }
    }
}

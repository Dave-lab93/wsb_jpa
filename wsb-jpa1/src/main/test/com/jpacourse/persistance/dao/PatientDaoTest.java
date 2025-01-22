package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;


    @Test
    @Transactional
    public void testFindByDrugName() {

        String drugName = "Ibuprom";
        List<PatientEntity> patients = patientDao.findByDrug(drugName);

        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(1);

    }

    /**
     * Test usuwania pacjenta i sprawdzenia kaskadowego usuwania wizyt.
     */

}

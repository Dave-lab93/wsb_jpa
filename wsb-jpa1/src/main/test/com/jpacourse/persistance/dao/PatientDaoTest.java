package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.Dao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Test
    public void testShouldFindAddressById() {
        // when
        AddressEntity addressEntity = (AddressEntity) addressDao.findOne(1L);
        // then
        assertThat(addressEntity).isNotNull();
        assertThat(addressEntity.getPostalCode()).isEqualTo("62-030");
    }

    @Transactional
    @Test
    public void testShouldSaveAndRemoveAddress() {
        // given
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("line1");
        addressEntity.setAddressLine2("line2");
        addressEntity.setCity("City1");
        addressEntity.setPostalCode("66-666");

        // when
        final AddressEntity saved = (AddressEntity) addressDao.save(addressEntity);
        assertThat(saved.getId()).isNotNull();
        final AddressEntity newSaved = (AddressEntity) addressDao.findOne(saved.getId());
        assertThat(newSaved).isNotNull();

        addressDao.delete(saved.getId());

        // then
        final AddressEntity removed = (AddressEntity) addressDao.findOne(saved.getId());
        assertThat(removed).isNull();
    }
}

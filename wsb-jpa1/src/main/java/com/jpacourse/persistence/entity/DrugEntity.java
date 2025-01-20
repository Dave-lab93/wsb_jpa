package com.jpacourse.persistence.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Drug")
@Where(clause = "name IS NOT NULL")
public class DrugEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public PatientEntity getPatient() { return patient; }
    public void setPatient(PatientEntity patient) { this.patient = patient; }

    public void save(DrugEntity drug1) {
    }

    public List<DrugEntity> findByPatient(PatientEntity patient) {

        return List.of();
    }
}

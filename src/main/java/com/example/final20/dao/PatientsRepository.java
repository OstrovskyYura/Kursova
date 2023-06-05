package com.example.final20.dao;

import com.example.final20.entities.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientsRepository extends JpaRepository<Patients, Integer> {
    List<Patients> findByNameNotNullOrderByName();
}
package com.example.final20.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = " profession", nullable = false, length = 200)
    private String profession;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Reception> receptions = new LinkedHashSet<>();

}
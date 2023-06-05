package com.example.final20.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reception")
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "turn")
    private Integer turn;

    @Column(name = "time", nullable = false, length = 100)
    private String time;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany
    @JoinTable(name = "doctor_reception",
            joinColumns = @JoinColumn(name = "reception_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Doctor> doctors = new LinkedHashSet<>();

}
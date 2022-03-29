package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entities {
    @Id
    @Column(columnDefinition = "VARCHAR(255)",nullable = false)
    private String entitiesId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate outDate;

    @Column(columnDefinition = "VARCHAR(255)")
    private String status;

    private float weight;

    private Boolean isDelete;
    private String cageId;

    @ManyToOne(targetEntity = Cage.class)
    @JoinColumn(name = "cageId",nullable = false, referencedColumnName = "cageId",insertable = false,updatable = false)
    @JsonBackReference
    private Cage cage;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entities", cascade = CascadeType.REMOVE)
    private Set<EntitiesIllness> entities;
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cage {
    @Id
    @NotBlank
    @Column(columnDefinition = "VARCHAR(255)")
    private String cageId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate closedDate;

    private int quantity;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employeeCage;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cage", cascade = CascadeType.REMOVE)
    @JsonBackReference(value = "JsonBackEntities")
    private Set<Entities> entities;
}

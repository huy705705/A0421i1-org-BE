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
    @Column(columnDefinition = "VARCHAR(255)")
    private String entitiesId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate outDate;

    @Column(columnDefinition = "VARCHAR(255)")
    private String status;

    private float weight;

    private Boolean isDelete;

    @ManyToOne(targetEntity = Cage.class)
    @JoinColumn(name = "cageId", referencedColumnName = "cageId")
    @JsonBackReference
    private Cage cage;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entities", cascade = CascadeType.REMOVE)
    @JsonBackReference

    private Set<EntitiesIllness> entities;

    public String getEntitiesId() {
        return entitiesId;
    }

    public void setEntitiesId(String entitiesId) {
        this.entitiesId = entitiesId;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }

    public Set<EntitiesIllness> getEntities() {
        return entities;
    }

    public void setEntities(Set<EntitiesIllness> entities) {
        this.entities = entities;
    }
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entities {
    @Id
    @NotBlank(message = "{Mã vật nuôi không được trống !}")
    @Pattern(regexp = "^[0-9]{4}+$", message = "{Mã vật nuôi sai định dạng")
    @Column(columnDefinition = "VARCHAR(255)",nullable = false)
    private String entitiesId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate outDate;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank(message = "{Status can't null}")
    private String status;

    private float weight;

    private Boolean isDelete;
    private String cageId;

    @ManyToOne(targetEntity = Cage.class)
    @JoinColumn(name = "cageId",nullable = false, referencedColumnName = "cageId",insertable = false,updatable = false)
    @JsonBackReference
    private Cage cage;


    public String getEntitiesId() {
        return entitiesId;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public String getStatus() {
        return status;
    }

    public float getWeight() {
        return weight;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public String getCageId() {
        return cageId;
    }

    public Cage getCage() {
        return cage;
    }


    public void setEntitiesId(String entitiesId) {
        this.entitiesId = entitiesId;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }
}

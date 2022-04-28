package com.example.demo.model.category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Cat_Ward {
        @Id
        @Column(columnDefinition = "int",nullable = false)
        private int wardId;

        @Column(columnDefinition = "VARCHAR(50)")
        @NotBlank
        private String wardName;

        @Column(columnDefinition = "VARCHAR(50)")
        @NotBlank
        private String wardPrefix;

//        @Column(columnDefinition = "int")
//        @NotBlank
//        private int districtId;

        @ManyToOne(targetEntity = Cat_District.class)
        @JoinColumn(name = "district_id",nullable = false,columnDefinition = "int")
        private Cat_District district;

        @ManyToOne(targetEntity = Cat_Province.class)
        @JoinColumn(name = "province_id",nullable = false,columnDefinition = "int")
        private Cat_Province province;

}

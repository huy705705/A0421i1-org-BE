package com.example.demo.model.category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cat_District {
        @Id
        @Column(columnDefinition = "int",nullable = false)
        private int districtId;

        @Column(columnDefinition = "VARCHAR(50)")
        @NotBlank
        private String districtName;

        @Column(columnDefinition = "VARCHAR(50)")
        @NotBlank
        private String districtPrefix;

//        @Column(columnDefinition = "int")
//        @NotBlank
//        private int provinceId;

        @ManyToOne(targetEntity = Cat_Province.class)
                @JoinColumn(name = "province_id",nullable = false,columnDefinition = "int")
        private Cat_Province province;

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "district", cascade = CascadeType.REMOVE)
        @JsonBackReference(value = "JsonBackWard")
        private Set<Cat_Ward> wards;
}

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
public class Cat_Province {
        @Id
        @Column(columnDefinition = "int",nullable = false)
        private int provinceId;

        @Column(columnDefinition = "VARCHAR(50)")
        @NotBlank
        private String provinceName;

        @Column(columnDefinition = "VARCHAR(50)")
        @NotBlank
        private String provinceCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "province", cascade = CascadeType.REMOVE)
    @JsonBackReference(value = "JsonBackDistrict")
    private Set<Cat_District> districts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "province", cascade = CascadeType.REMOVE)
    @JsonBackReference(value = "JsonBackProvinceToWard")
    private Set<Cat_Ward> wards;

    public int getId() {
        return provinceId;
    }

    public String getProvince_name() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}

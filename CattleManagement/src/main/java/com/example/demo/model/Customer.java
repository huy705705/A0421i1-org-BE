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
public class Customer {
    @Id
    @NotBlank
    @Column(columnDefinition = "VARCHAR(255)")
    private String customerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    private String fullName;

    private String address;

    private String phone;

    private String email;

    private String message;

    private Boolean isDelete;

// Hoi Thang lai cho One to One
    
}

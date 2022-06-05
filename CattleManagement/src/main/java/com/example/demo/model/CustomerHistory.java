package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerHistory {
    @Id
    @Column(columnDefinition = "int")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int customerHistoryId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate modifyDate;

    private int customerId;

    private String employeeId;

    private String message;

    private String status;

    private String comment;

}

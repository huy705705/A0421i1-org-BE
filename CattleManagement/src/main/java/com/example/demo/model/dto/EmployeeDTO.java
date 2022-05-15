package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    String employeeId;
    String employeeName;
    String avatar;
    String email;
    String birthday;
    String gender;
    String idCard;
    String address;
    boolean isDelete;
    Long accountId;
    String accountName;
    String password;
}

package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService extends IGeneralService<Employee> {
    Page<Employee> findAllByEmployeeNameContaining(String employeeName, Pageable pageable);
}

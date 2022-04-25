package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService extends IGeneralService<Employee> {
    Page<Employee> findAllEmployeeByNameAndId(String searchName, String searchId, Pageable pageable);


}

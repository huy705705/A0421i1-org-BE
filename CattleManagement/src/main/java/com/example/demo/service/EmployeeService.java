package com.example.demo.service;

import com.example.demo.model.Employee;

import com.example.demo.model.dto.EmployeeForCageDto;

import com.example.demo.model.dto.GetEmployeeNameDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService extends IGeneralService<Employee> {
    Page<Employee> findAllEmployeeByNameAndId(String searchName, String searchId, Pageable pageable);


    Boolean existsByEmployeeId(String id);

    List<EmployeeForCageDto> getAllEmployee();

    Employee findEmployeeById(String id);

    List<GetEmployeeNameDTO> getAllEmployeeName();

}

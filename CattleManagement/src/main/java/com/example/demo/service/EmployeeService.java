package com.example.demo.service;

import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.dto.EmployeeFindIdDTO;
import com.example.demo.model.dto.EmployeeListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService{
    Page<EmployeeListDTO> findAllEmployeeByNameAndId(String searchName, String searchId, Pageable pageable);
    Page<EmployeeListDTO> findAllEmployee(Pageable pageable);
    Optional<EmployeeFindIdDTO> findEmployeeById(String id);
    void editEmployee(EmployeeDTO EmployeeDTO);
    void createNewEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(String id);
    Integer getNextId();
    void updateAutoRender();
}

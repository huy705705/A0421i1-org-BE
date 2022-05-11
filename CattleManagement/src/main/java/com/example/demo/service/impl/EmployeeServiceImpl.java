package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.dto.EmployeeForCageDto;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Page<Employee> findAllEmployeeByNameAndId(String searchName, String searchId, Pageable pageable) {
        return employeeRepo.findAllEmployeeByNameAndId(searchName, searchId, pageable);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepo.findAll(pageable);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public void remove(String id) {
        employeeRepo.deleteById(id);
    }


    // thangTD use to create Cage
    @Override
    public Boolean existsByEmployeeId(String id) {
        return employeeRepo.existsByEmployeeId(id);
    }

    @Override
    public List<EmployeeForCageDto> getAllEmployee() {
        return employeeRepo.getAllEmployee();
    }

    @Override
    public Employee findEmployeeById(String id) {
        return employeeRepo.findEmployeeById(id);
    }


}

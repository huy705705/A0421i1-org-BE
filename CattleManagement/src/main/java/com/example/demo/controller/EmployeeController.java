package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/list")
    public ResponseEntity<Page<Employee>> listEmployee(@PageableDefault(size = 2) Pageable pageable,
                                                       @RequestParam Optional<String> search) {
        Page<Employee> employees;

        if (search.isPresent()) {
            employees = employeeService.findAllByEmployeeNameContaining(search.get(), pageable);
        } else{
            employees = employeeService.findAll(pageable);
        }

        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (!employeeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employee.setEmployeeId(employeeOptional.get().getEmployeeId());
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

}

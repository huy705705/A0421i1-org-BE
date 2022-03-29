package com.example.demo.repository;


import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, String> {
    Page<Employee> findAllByEmployeeNameContaining(String employeeName, Pageable pageable);
}

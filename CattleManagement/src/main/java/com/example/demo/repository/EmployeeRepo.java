package com.example.demo.repository;


import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, String> {
//    Page<Employee> findAllEmployeeName(String employeeName, Pageable pageable);

    @Query(value = "select * from Employee where employeeName like ?", nativeQuery = true)
    Page<Employee> findAllEmployeeName(String employeeName, Pageable pageable);
}

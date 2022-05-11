package com.example.demo.repository;


import com.example.demo.model.Employee;
import com.example.demo.model.dto.EmployeeForCageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, String> {

    @Query(value =  "select employee.employee_id, employee.employee_name, employee.avartar, employee.birthday, \n" +
                    "employee.id_card, employee.address, employee.gender, employee.email, employee.is_delete, \n" +
                    "employee.account_id from employee \n" +
                    "join account on account.account_id = employee.account_id \n" +
                    "where employee.employee_name like ?1 and employee.employee_id like ?2 ", nativeQuery = true)
    Page<Employee> findAllEmployeeByNameAndId(String searchName, String searchId, Pageable pageable);


    @Query(value = "SELECT * FROM employee  where is_delete!=1 or is_delete=null", nativeQuery = true)
    Page<Employee> findAll(Pageable pageable);

//    @Query(value =  "select employee.employee_id as employeeId, employee.employee_name as employeeName, employee.birthday, \n" +
//                    "employee.id_card as idCard, employee.address, employee.gender, employee.email, \n" +
//                    "employee.account_name as accountName, employee.account_password as accountPassword from employee \n" +
//                    "left join account on account.account_id = employee.account_id \n" +
//                    "where employee.employee_id like ?1", nativeQuery = true)
//    Page<Employee> findById(String Id, Pageable pageable);


    // thangTD use to create Cage
    Boolean existsByEmployeeId(String id);

    @Query(value = "SELECT e.employee_id as employeeId, e.employee_name as employeeName FROM employee e where is_delete!=1 or is_delete=null", nativeQuery = true)
    List<EmployeeForCageDto> getAllEmployee();

    @Query(value = "SELECT * FROM employee  where employee_id=?", nativeQuery = true)
    Employee findEmployeeById (String id);
}

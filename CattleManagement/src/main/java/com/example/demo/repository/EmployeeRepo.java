package com.example.demo.repository;


import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, String> {

    @Query(value =  "select employee.employee_id, employee.employee_name, employee.avartar, employee.birthday, \n" +
                    "employee.id_card, employee.address, employee.gender, employee.email, employee.is_delete, \n" +
                    "employee.account_id from employee \n" +
                    "join account on account.account_id = employee.account_id \n" +
                    "where employee.employee_name like ?1 and employee.employee_id like ?2 ", nativeQuery = true)
    Page<Employee> findAllEmployeeByNameAndId(String searchName, String searchId, Pageable pageable);


//    @Query(value =  "select employee.employee_id, employee.employee_name, employee.avartar, employee.birthday, " +
//            "employee.id_card, employee.address, employee.gender, employee.email, employee.is_delete, " +
//            "employee.account_id from employee e " +
//            "left join account on account.account_id = employee.account_id", nativeQuery = true)
//    Page<Employee> findAll(Pageable pageable);


//    @Query(value =  "select employee.employee_id as employeeId, employee.employee_name as employeeName, employee.birthday, \n" +
//                    "employee.id_card as idCard, employee.address, employee.gender, employee.email, \n" +
//                    "employee.account_name as accountName, employee.account_password as accountPassword from employee \n" +
//                    "left join account on account.account_id = employee.account_id \n" +
//                    "where employee.employee_id like ?1", nativeQuery = true)
//    Page<Employee> findById(String Id, Pageable pageable);
}

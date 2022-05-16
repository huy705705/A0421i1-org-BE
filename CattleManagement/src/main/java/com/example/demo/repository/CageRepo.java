package com.example.demo.repository;

import com.example.demo.model.Cage;
import com.example.demo.model.Employee;
import com.example.demo.model.Entities;
import com.example.demo.model.dto.CageForEditDto;
import com.example.demo.model.dto.EmployeeForCageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CageRepo extends JpaRepository<Cage, String> {

    @Query(value = "select cage_id from cage",nativeQuery = true)
    List<String> getListCageId();

    Optional<Cage> findByCageId(String Id);

    @Query(value="select c.cage_id as cageId, c.employee_id as employeeId, c.created_date as createdDate, c.closed_date as closedDate, " +
            "c.quantity as quantity, e.employee_name as employeeName from cage c join employee e on c.employee_id = e.employee_id" +
            " where c.cage_id =? ", nativeQuery=true)
    Optional<CageForEditDto> findByCageId2(String Id);

    Boolean existsByCageId(String id);

    @Query(value="SELECT * FROM cages_seq", nativeQuery=true)
    Integer getNextId();

    @Query(value="select e.employee_name as employeeName, e.employee_id as employeeId from employee e join account a on e.account_id = a.account_id where a.account_name = ? limit 1", nativeQuery=true)
    EmployeeForCageDto getEmployeeIdAndName(String accountName);
}
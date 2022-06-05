package com.example.demo.repository;

import com.example.demo.model.CustomerHistory;
import com.example.demo.model.dto.CustomerHistoryListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@Repository
public interface CustomerHistoryRepo extends JpaRepository<CustomerHistory,String> {
    @Query(value="SELECT ch.created_date as createdDate,ch.modify_date as modifyDate,ch.comment,ch.status,ch.message,c.full_name as fullName,e.employee_name as employeeName from customer_history ch left join customer c on " +
            "ch.customer_id=c.customer_id " +
            "left join employee e on ch.employee_id=e.employee_id where ch.customer_id=?1",nativeQuery=true)
    Page<CustomerHistoryListDTO> getCustomerHistory(Integer customerId, Pageable pagealbe);
    @Modifying
    @Transactional
    @Query(value = "insert into customer_history(created_date,modify_date,customer_id,employee_id,`comment`,`status`,message) " +
            "values(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void saveHistory(String createDate,String modifyDate, Integer customerId, String employeeId, String comment, String status, String message);

}

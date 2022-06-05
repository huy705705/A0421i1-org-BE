package com.example.demo.service;

import com.example.demo.model.CustomerHistory;
import com.example.demo.model.dto.CustomerHistoryListDTO;
import com.example.demo.model.dto.CustomerModifyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerHistoryService {
    Page<CustomerHistoryListDTO> getCustomerHistory(Integer customerId, Pageable pageable);
    void saveHistory(CustomerModifyDTO customer, Integer customerId,String employeeId) ;
}

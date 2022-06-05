package com.example.demo.service.impl;

import com.example.demo.model.CustomerHistory;
import com.example.demo.model.dto.CustomerHistoryListDTO;
import com.example.demo.model.dto.CustomerModifyDTO;
import com.example.demo.repository.CustomerHistoryRepo;
import com.example.demo.service.CustomerHistoryService;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerHistoryServiceImpl implements CustomerHistoryService {
    @Autowired
    private CustomerHistoryRepo customerHistoryRepo;

    @Override
    public Page<CustomerHistoryListDTO> getCustomerHistory(Integer customerId, Pageable pageable) {
        return customerHistoryRepo.getCustomerHistory(customerId,pageable);
    }

    @Override
    public void saveHistory(CustomerModifyDTO customer, Integer customerId,String employeeId) {

        customerHistoryRepo.saveHistory(customer.getCreateDate(),customer.getModifyDate(),customerId,employeeId,customer.getComment(),customer.getStatus(),customer.getMessage());
    }
}

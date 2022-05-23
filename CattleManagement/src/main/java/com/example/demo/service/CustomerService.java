package com.example.demo.service;

import com.example.demo.model.dto.CustomerModifyDTO;
import com.example.demo.model.dto.CustomerListDTO;
import com.example.demo.model.dto.GetOldCustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerService {
    void createCustomer(CustomerModifyDTO customer);
    Page<CustomerListDTO> getAllCustomer(Pageable pageable);
    GetOldCustomerDTO getUpdateCustomer(String email, String phone);
    Boolean existsByCustomerId(Integer customerId);
    void updateCustomer(CustomerModifyDTO customer, Integer customerId);
}


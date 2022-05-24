package com.example.demo.service.impl;

import com.example.demo.model.dto.CustomerModifyDTO;
import com.example.demo.model.dto.CustomerListDTO;
import com.example.demo.model.dto.GetOldCustomerDTO;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public void createCustomer(CustomerModifyDTO customer) {
        customerRepo.createCustomer(customer.getFullName(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getGender(),
                customer.getMessage(),
                customer.getProvince(),
                customer.getDistrict(),
                customer.getWard(),
                customer.getCreateDate());
    }

    @Override
    public Page<CustomerListDTO> getAllCustomer(Pageable pageable) {
        return null;
    }

    @Override
    public GetOldCustomerDTO getUpdateCustomer(String email, String phone) {
        return customerRepo.getUpdatedCustomer(email,phone);
    }

    @Override
    public Boolean existsByCustomerId(Integer customerId) {
        return customerRepo.existsByCustomerId(customerId);
    }

    @Override
    public void updateCustomer(CustomerModifyDTO customer, Integer customerId) {
        customerRepo.updateCustomer(customer.getFullName(),customer.getAddress(),customer.getPhone(),customer.getEmail(),
                customer.getGender(),customer.getMessage(),customer.getProvince(),customer.getDistrict(),customer.getWard(),customer.getCreateDate(),customerId);

    }

}
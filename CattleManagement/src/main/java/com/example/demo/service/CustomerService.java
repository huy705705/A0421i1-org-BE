package com.example.demo.service;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Service;


public interface CustomerService {
    Customer save(Customer customer);
}


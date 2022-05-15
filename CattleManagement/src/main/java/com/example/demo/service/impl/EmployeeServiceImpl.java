package com.example.demo.service.impl;

import com.example.demo.model.Account;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.dto.EmployeeEditDTO;
import com.example.demo.model.dto.EmployeeFindIdDTO;
import com.example.demo.model.dto.EmployeeListDTO;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.AccountService;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private AccountService accountService;

    @Override
    public Page<EmployeeListDTO> findAllEmployeeByNameAndId(String searchName, String searchId, Pageable pageable) {
        return employeeRepo.findAllEmployeeByNameAndId(searchName, searchId, pageable);
    }

    @Override
    public Page<EmployeeListDTO> findAllEmployee(Pageable pageable) {
        return employeeRepo.findAllEmployee(pageable);
    }

    @Override
    public Optional<EmployeeFindIdDTO> findEmployeeById(String id) {
        return employeeRepo.findEmployeeById(id);
    }

    @Override
    public void deleteEmployee(String id) {
        employeeRepo.deleteEmployee(id);
    }

    @Override
    public void createNewEmployee(EmployeeDTO employeeDTO) {
        Account account = new Account();
        account.setAccountName(employeeDTO.getAccountName());
        account.setPassword(employeeDTO.getPassword());
        account.setEmail(employeeDTO.getEmail());
        accountService.addNew(account.getAccountName(),account.getPassword(),account.getEmail());
        account.setAccountId(accountService.findAccountIdByAccountName(employeeDTO.getAccountName()));
        employeeRepo.createNewEmployee( employeeDTO.getEmployeeId(), employeeDTO.getEmployeeName(), employeeDTO.getBirthday(),
                                        employeeDTO.getIdCard(), employeeDTO.getAddress(), employeeDTO.getGender(),
                                        employeeDTO.getEmail(), account.getAccountId(), false);
    }

    @Override
    public void editEmployee(EmployeeDTO employeeDTO) {
        accountService.editAccount(employeeDTO.getAccountName(), employeeDTO.getPassword(), employeeDTO.getAccountId());

        employeeRepo.editEmployee(employeeDTO.getEmployeeName(), employeeDTO.getBirthday(),
                employeeDTO.getIdCard(), employeeDTO.getAddress(), employeeDTO.getGender(),
                employeeDTO.getEmail(), employeeDTO.getEmployeeId());
    }
}

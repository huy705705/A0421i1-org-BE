package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Employee;
import com.example.demo.service.AccountService;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AccountService accountService;

    @GetMapping("")
    public ResponseEntity<Page<Employee>> listEmployee(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(defaultValue = "") String searchName,
            @RequestParam(defaultValue = "") String searchId) {
        Page<Employee> employees;
        if (!searchName.equals("") || !searchId.equals("")) {
            return new ResponseEntity<Page<Employee>>(employeeService.findAllEmployeeByNameAndId
                    ("%" + searchName + "%", "%" + searchId + "%", pageable),(HttpStatus.OK));
        } else{
            employees = employeeService.findAll(pageable);
        }

        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> accountList = accountService.getAllAccount();
        if (accountList.isEmpty()) {
            return new ResponseEntity<List<Account>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
    }
    
    @GetMapping("/update/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable String id) {
        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (!employeeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@Validated @PathVariable String id,
                                                   @RequestBody Employee employee, BindingResult bindingResult) {
        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (!employeeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
        }else {
            employee.setEmployeeId(employeeOptional.get().getEmployeeId());
            return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

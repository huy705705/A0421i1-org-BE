package com.example.demo.controller;

import com.example.demo.model.Cage;
import com.example.demo.model.Employee;
import com.example.demo.model.dto.CageCreateDto;
import com.example.demo.model.dto.CageEditDto;
import com.example.demo.model.dto.EmployeeForCageDto;
import com.example.demo.service.impl.CageServiceImpl;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
//@RequestMapping("employee/cage")
@RequestMapping("api/public")
@CrossOrigin(origins = "http://localhost:4200")
public class CageController {
    @Autowired
    private CageServiceImpl cageService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    // get employee from login account to FE when create cage
    // send auto increase Id to FE (done)
    // validate openDate vs closeDate (done)
    // autoIncreaseId in create function (done)
    // not update openDate (done)
    // get outDate of entities for closeDate (done)
    // auto update closeDate (done)

    //ok
    @GetMapping("/createId")
    public ResponseEntity<Integer> getCageIdForCreate() {
       int cageId = cageService.getNextId();
       return new ResponseEntity<>(cageId,HttpStatus.OK);
    }

    // get current employeeId for create (fix)
    @GetMapping("/employeeId")
    public ResponseEntity<?> getEmployeeIdForCreate(){
        return null;
    }
    
    // fix
    @GetMapping(value = "/username")
    @ResponseBody
    public ResponseEntity<?> currentUserName(Authentication authentication) {
        System.out.println(authentication.getName());
        String username =  authentication.getName();
        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    // ok
    @PostMapping("/create")
    public ResponseEntity<?> createCage(@Valid @RequestBody CageCreateDto cageCreateDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
        }

        Cage cage = new Cage();

        Map<String, String> listErrors = new HashMap<>();

        System.out.println("Mã nhân viên tạo: " + (cageCreateDto.getEmployeeId()));
        if (!cageService.checkEmployee(cageCreateDto.getEmployeeId())) {
            System.out.println("tên employee không có ");
            listErrors.put("employeeError", "Tên nhân viên không tồn tại.");
        }

        BeanUtils.copyProperties(cageCreateDto, cage);

        if (cageService.existsByCageId(cage.getCageId())) {
            System.out.println("Tên chuồng nuôi đã tồn tại");
            listErrors.put("cageError", "Mã chuồng nuôi đã tồn tại.");
        }

        if (!cageService.isValidDate(cage)){
            System.out.println("Lỗi ngày tạo và đóng chuồng.");
            listErrors.put("cageError", "Lỗi ngày tạo và đóng chuồng.");
        }

        Employee employee = employeeService.findEmployeeById(cageCreateDto.getEmployeeId());
        if (employee != null) {
            cage.setEmployee(employee);
        } else {
            System.out.println("Lỗi tim thong tin nhan vien.");
            listErrors.put("employeeError", "Khong tim thay thong tin nhan vien.");
        }
        if (!listErrors.isEmpty()) {
            System.out.println(listErrors.keySet());
            return ResponseEntity.badRequest().body(listErrors);
        }

        cageService.save(cage);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    // for edit
    @GetMapping("/listEmployee")
    public ResponseEntity<?> getListEmployeeForEdit(){
        List<EmployeeForCageDto> employeeList = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    // ok
    @GetMapping("/edit/{id}")
    public ResponseEntity<?> findCageByIdForEdit(@PathVariable String id) {
        Optional<Cage> cage = cageService.findCageById(id);
        if (!cage.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cage.get(), HttpStatus.OK);
    }

    // ok
    @PatchMapping(value = "/edit/{id}")
    public ResponseEntity<?> updateCage(@RequestBody @Valid CageEditDto cageEditDto, BindingResult bindingResult, @PathVariable String id){

        Optional<Cage> cageOptional = cageService.findCageById(id);
        if (!cageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.NOT_ACCEPTABLE);
        }
        Cage cage = cageOptional.get();
        System.out.println("cage after find by id: "+cage.toString());
        Map<String, String> listErrors = new HashMap<>();

        if (!cageService.checkEmployee(cageEditDto.getEmployeeId())) {
            System.out.println("tên employee không có ");
            listErrors.put("employeeError", "Tên nhân viên không tồn tại.");
        }

        BeanUtils.copyProperties(cageEditDto, cage);

        if (!cageService.isValidDate(cage)){
            System.out.println("Lỗi ngày tạo và đóng chuồng.");
            listErrors.put("cageError", "Lỗi ngày tạo và đóng chuồng.");
        }

        Employee employee = employeeService.findEmployeeById(cageEditDto.getEmployeeId());
        cage.setEmployee(employee);

        if (!listErrors.isEmpty()) {
            System.out.println(listErrors.keySet());
            return ResponseEntity.badRequest().body(listErrors);
        }

        cageService.save(cage);
        return new ResponseEntity<>(cage, HttpStatus.OK);
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

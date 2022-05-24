package com.example.demo.controller;
import com.example.demo.model.dto.*;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/public/contact")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerModifyDTO customer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_MODIFIED);
        }
        else {
            customer.setCreateDate(LocalDate.now().toString());
            customerService.createCustomer(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<CustomerModifyDTO> updateCustomer(@RequestBody CustomerModifyDTO customer,
                                                            @PathVariable Integer id){
        Boolean checked=customerService.existsByCustomerId(id);
        System.out.println("+++++++++++++++++++Checked"+checked);
        if(checked){
            customer.setCreateDate(LocalDate.now().toString());
            customerService.updateCustomer(customer,id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/checkInfo")
    public ResponseEntity<GetOldCustomerDTO> checkCustomerInfo(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "phone") String phone){
        GetOldCustomerDTO customer=customerService.getUpdateCustomer(email,phone);
        if(customer!=null){
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }
        return null;

    }
    @GetMapping("")
    public ResponseEntity<List<ProvinceDTO>> getListProvince(){
        List<ProvinceDTO>catProvinceList=categoryService.getProvinceList();
        if(catProvinceList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(catProvinceList, HttpStatus.OK);
        }
    }
    @GetMapping(value ="/district/{id}")
    public ResponseEntity<List<DistrictListDTO>> getDistrictList(@PathVariable Integer id){
        List<DistrictListDTO> catDistrictList=categoryService.getDistrictList(id);
        if(catDistrictList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(catDistrictList, HttpStatus.OK);
        }
    }
    @GetMapping("/ward/{id}")
    public ResponseEntity<List<WardDTO>> getWardList(@PathVariable Integer id){
        List<WardDTO> wardDTOList= categoryService.getWardsList(id);
        if (wardDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(wardDTOList,HttpStatus.OK);
    }

}
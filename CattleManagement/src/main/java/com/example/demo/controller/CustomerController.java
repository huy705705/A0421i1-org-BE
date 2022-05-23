package com.example.demo.controller;

import com.example.demo.model.dto.DistrictListDTO;
import com.example.demo.model.dto.ProvinceDTO;
import com.example.demo.model.dto.WardDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/public/contact")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getListProvince(){
        List<ProvinceDTO>catProvinceList=categoryService.getProvinceList();
        System.out.println(catProvinceList.size());
        if(catProvinceList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(catProvinceList, HttpStatus.OK);
        }
    }
    @GetMapping(value ="/district/{id}")
    public ResponseEntity<?> getDistrictList(@PathVariable Integer id){
        List<DistrictListDTO> catDistrictList=categoryService.getDistrictList(id);
        if(catDistrictList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(catDistrictList, HttpStatus.OK);
        }
    }
   @GetMapping("/ward/{id}")
    public ResponseEntity<?> getWardList(@PathVariable Integer id){
        List<WardDTO> wardDTOList= categoryService.getWardsList(id);
        if (wardDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(wardDTOList,HttpStatus.OK);
   }

}

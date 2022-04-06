package com.example.demo.controller;

import com.example.demo.model.Entities;
import com.example.demo.service.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/entities")

public class EntitiesController {
    @Autowired
    private EntitiesService entitiesService;
    @PostMapping("/create")
    public ResponseEntity<?> createEntities(@Valid @RequestBody Entities entities, BindingResult bindingResult) throws Exception  {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_MODIFIED);
        }
        else {
            return new ResponseEntity<>(entitiesService.save(entities), HttpStatus.CREATED);
        }
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<Entities> findEntitiesById(@PathVariable String id) {
        Optional<Entities> entitiesOptional = entitiesService.findById(id);
        if (!entitiesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entitiesOptional.get(), HttpStatus.OK);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Entities> updateCustomer(@PathVariable String id, @RequestBody Entities entities) {
        Optional<Entities> entitiesOptional = entitiesService.findById(id);
        if (!entitiesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //entities.setEntitiesId(entitiesOptional.get().getEntitiesId());
        return new ResponseEntity<>(entitiesService.save(entities),HttpStatus.OK);
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

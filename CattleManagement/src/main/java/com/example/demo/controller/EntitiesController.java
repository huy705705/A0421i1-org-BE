package com.example.demo.controller;

import com.example.demo.model.Entities;
import com.example.demo.repository.EntitiesIllnessRepo;
import com.example.demo.repository.EntitiesRepo;
import com.example.demo.service.CageService;
import com.example.demo.service.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/entities")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class EntitiesController {
    @Autowired
    private EntitiesService entitiesService;
    @Autowired
    private CageService cageService;
    @GetMapping("/create")
    public ResponseEntity<?> getListCageId() {
        List<String> cageList = cageService.getListCageId();
        if (cageList.size()==0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(cageList,HttpStatus.OK);
        }

    }
    @GetMapping("/createId/{cageId}")
    public ResponseEntity<String> getEntitiesId(@PathVariable String cageId) {
        Integer currentId=entitiesService.getEntitiesId(cageId);
        String entitiesId=currentId.toString();
        return new ResponseEntity<>(entitiesId,HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<Page<Entities>> findAllEntities(@PageableDefault(size = 10) Pageable pageable){
        Page<Entities> entities= entitiesService.findAll(pageable);
        if (entities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities,HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Entities> findEntitiesById(@PathVariable String id) {
        Optional<Entities> entitiesOptional = entitiesService.findById(id);
        if (!entitiesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(entitiesOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<Entities> findEntitiesByIdToUpdate(@PathVariable String id) {
        Optional<Entities> entitiesOptional = entitiesService.findById(id);
        if (!entitiesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(entitiesOptional.get(), HttpStatus.OK);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<?> createEntities(@Valid @RequestBody Entities entities, BindingResult bindingResult) throws Exception  {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_MODIFIED);
        }
        else {
            entities.setEntitiesId(entitiesService.getEntitiesId(entities.getCageId()).toString());
            entitiesService.updateAutoRender(entities.getCageId());
            return new ResponseEntity<>(entitiesService.save(entities), HttpStatus.CREATED);
        }

    }



    @PatchMapping("/delete/{id}")
    public ResponseEntity<Entities> deleteEntities(@PathVariable String id) {
        Optional<Entities> entitiesOptional = entitiesService.findById(id);
        if (!entitiesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entitiesOptional.get().setDelete(false);
        return new ResponseEntity<>(entitiesService.save(entitiesOptional.get()), HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/update/{id}",consumes ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Entities> updateCustomer(@PathVariable String id, @RequestBody Entities entities) {
        Optional<Entities> entitiesOptional = entitiesService.findById(id);
        if (!entitiesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //entitiesOptional.get().setDelete(false);
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
        System.out.println(errors.toString());
        return errors;
    }

}

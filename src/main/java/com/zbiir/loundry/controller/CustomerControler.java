package com.zbiir.loundry.controller;

import com.zbiir.loundry.model.Customer;
import com.zbiir.loundry.model.CustomerDTO;
import com.zbiir.loundry.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerControler {

    @Autowired
    private CustomerService customerService;

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable long id){
        customerService.deleteCustomer(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id){
            Optional<Customer> customer = customerService.getSingleCustomer(id);
            if(customer.isPresent()){
               return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String,String> errorMap = new HashMap<String,String>();
        ex.getBindingResult().getAllErrors().forEach( (err) -> {
            String fieldName = ((FieldError) err).getField();
            String fieldMesage = err.getDefaultMessage();
            errorMap.put(fieldName,fieldMesage);

        });

        return errorMap;
    }



}

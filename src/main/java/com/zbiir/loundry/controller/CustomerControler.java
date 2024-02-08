package com.zbiir.loundry.controller;

import com.zbiir.loundry.exception.IdOutOfBoudException;
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
    @GetMapping("/find")
    public List<Customer> findAllContent(@RequestParam String find){
        return customerService.findCustmer(find);

    }


    @PutMapping("/update/{id}")
    public Customer updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable long id){
        Customer createdCustomer =
                customerService.updateCustomer(new Customer(
                        id,
                        customerDTO.getName(),
                        customerDTO.getAddres(),
                        customerDTO.getPhone()
                ));
        return createdCustomer;
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        Customer createdCustomer =
                customerService.createCustomer(new Customer(
                    null,
                    customerDTO.getName(),
                    customerDTO.getAddres(),
                    customerDTO.getPhone()
                ));
        return new ResponseEntity(createdCustomer,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable long id){
        customerService.deleteCustomer(id);
    }
    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) throws IdOutOfBoudException {
         return new ResponseEntity<Customer>(customerService.getSingleCustomer(id), HttpStatus.OK);

    }


}
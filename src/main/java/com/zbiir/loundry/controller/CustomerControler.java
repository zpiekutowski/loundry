package com.zbiir.loundry.controller;

import com.zbiir.loundry.exception.DeleteActiveCustomerException;
import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.model.Customer;
import com.zbiir.loundry.model.CustomerDTO;
import com.zbiir.loundry.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerControler {

     @Autowired
    private CustomerService customerService;

    @CrossOrigin
    @GetMapping("/find")
    public List<Customer> findAllContent(@RequestParam String find){
        return customerService.findCustmer(find);

    }

    @CrossOrigin
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
    public void deleteCustomer(@PathVariable long id) throws DeleteActiveCustomerException {
        customerService.deleteCustomer(id);
    }
    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) throws IdCustomerOutOfBoudException {
         return new ResponseEntity<Customer>(customerService.getSingleCustomer(id), HttpStatus.OK);

    }


}

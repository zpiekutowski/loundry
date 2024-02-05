package com.zbiir.loundry.service;

import com.zbiir.loundry.model.Customer;
import com.zbiir.loundry.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getSingleCustomer(long id){

        return customerRepository.findById(id);

    }

}

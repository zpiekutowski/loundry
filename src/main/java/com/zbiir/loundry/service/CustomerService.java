package com.zbiir.loundry.service;

import com.zbiir.loundry.model.Customer;
import com.zbiir.loundry.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getSingleCustomer(long id){

        return customerRepository.findById(id);

    }

    public Customer createCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> findCustmer(String find) {
        return customerRepository.findByNameContaining(find);
    }
}

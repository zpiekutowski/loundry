package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


    List<Customer> findByNameContaining(String find);
}

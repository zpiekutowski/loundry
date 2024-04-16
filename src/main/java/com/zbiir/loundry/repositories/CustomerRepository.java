package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    List<Customer> findByNameContaining(String find);
}

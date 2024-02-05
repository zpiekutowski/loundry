package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}

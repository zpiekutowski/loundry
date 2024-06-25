package com.zbiir.loundry.service;

import com.zbiir.loundry.exception.DeleteActiveCustomerException;
import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.model.Customer;
import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.OrderArchive;
import com.zbiir.loundry.repositories.CustomerRepository;
import com.zbiir.loundry.repositories.OrderArchiveRepository;
import com.zbiir.loundry.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderArchiveRepository orderArchiveRepository;
    @Value("${customer.deleted}")
    private Long idDeletedCustomer;


    public Customer getSingleCustomer(long id) throws IdCustomerOutOfBoudException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new IdCustomerOutOfBoudException("Klient poza zakresem:" + id);
        }

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


    public void deleteCustomer(long id) throws DeleteActiveCustomerException {
        if (id == 1)
            throw new DeleteActiveCustomerException("Nr 1 nie moze byc usunięty");

        List<Order> customerOrders = orderRepository.findOrdersByCustomerId(id);
        if (!customerOrders.isEmpty()) throw new DeleteActiveCustomerException("Klien aktywny, zamknij aktywne zamowienia");

        List<OrderArchive> customerArchiveOrders = orderArchiveRepository.findOrderArchiveByCustomerId(id);
        if (!customerArchiveOrders.isEmpty()) {
            Customer markAsDeleted = customerRepository.findById(idDeletedCustomer).get();
            customerArchiveOrders.forEach(n -> n.setCustomer(markAsDeleted));
        }

        customerRepository.deleteById(id);
    }

    public List<Customer> findCustmer(String find) {
        return customerRepository.findByNameContaining(find);
    }
}

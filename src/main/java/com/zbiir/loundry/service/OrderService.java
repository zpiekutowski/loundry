package com.zbiir.loundry.service;


import com.zbiir.loundry.exception.IdOutOfBoudException;
import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.Order;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    HttpSession session;

    private Order order;
    public Order newOrder() throws OrderExistException{
        Order order = (Order) session.getAttribute("order");
        if (order==null){
            order = new Order();
            session.setAttribute("order", order);
            order.setStartDate(LocalDate.now());
        }
        else{
            throw new OrderExistException("New order can NOT be placed, please close current order");
        }
        return order;
    }


    public Order setCustomerId(Long id) throws IdOutOfBoudException {
        order = (Order) session.getAttribute("order");
        order.setCustomer(customerService.getSingleCustomer(id));
        return order;
    }

    public String test() {
        return session.getId();
    }
}

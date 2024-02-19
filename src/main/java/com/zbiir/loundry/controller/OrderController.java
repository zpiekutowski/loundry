package com.zbiir.loundry.controller;

import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.IdServedUnitOutOfBoundException;
import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.UnitOrder;
import com.zbiir.loundry.model.UnitOrderDTO;
import com.zbiir.loundry.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    private Order order;

    @PostMapping("/add_unit")
    public Order addService(@RequestBody UnitOrderDTO unitOrderDTO) throws IdServedUnitOutOfBoundException {
        return orderService.newUnitOrder(unitOrderDTO);
    }

    @PostMapping("/setcustomer/{id}")
    public Order setCustomerId(@PathVariable Long id) throws IdCustomerOutOfBoudException {
        return orderService.setCustomerId(id);
    }
    @PostMapping("/new")
    public Order newOrder() throws OrderExistException {
        return orderService.newOrder();
       }

    @GetMapping("/current")
    public Order getcurrent(HttpSession session){
        order = (Order) session.getAttribute("order");
        return order;
    }
    @PostMapping("/save")
    public Order save(){
        return orderService.saveOrder();
    }

    @GetMapping("/test")
    public String test(){
        return orderService.test();

    }
}

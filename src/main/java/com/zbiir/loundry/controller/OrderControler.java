package com.zbiir.loundry.controller;

import com.zbiir.loundry.exception.IdOutOfBoudException;
import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.ServedUnit;
import com.zbiir.loundry.model.UnitOrder;
import com.zbiir.loundry.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderControler {

    @Autowired
    private OrderService orderService;
    private Order order;

    @PostMapping("/add_unit")
    public Order addService(@RequestBody UnitOrder unitOrder){
        return null;

    }

    @PostMapping("/setcustomer/{id}")
    public Order setCustomerId(@PathVariable Long id) throws IdOutOfBoudException {
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
    @GetMapping("/test")
    public String test(){
        return orderService.test();

    }

}

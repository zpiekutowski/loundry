package com.zbiir.loundry.controller;

import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.OrderDTO;
import com.zbiir.loundry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderControler {
    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List <OrderDTO> getActiveOrders(){
        return orderService.getActiveOrders();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable long id){
        Order order = orderService.getOrderbyId(id);
        return  (order != null)? ResponseEntity.ok(order) : (ResponseEntity<Order>) ResponseEntity.noContent();

    }

    //hiden endPoint should never be use
    @GetMapping("/allall")
    public List<Order> getAlAllOrders(){
        return orderService.getAllAllOrders();

    }


}

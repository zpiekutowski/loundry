package com.zbiir.loundry.controller;

import com.zbiir.loundry.model.ApiRespond;
import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.OrderDTO;
import com.zbiir.loundry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity <Order> getOrderDetails(@PathVariable long id){
        Order order = orderService.getOrderbyId(id);

        return  (order != null)? ResponseEntity.ok(order) : ResponseEntity.noContent().build();

    }

    //hiden endPoint should never be use
    @GetMapping("/allall")
    public List<Order> getAlAllOrders(){
        return orderService.getAllAllOrders();

    }
    @GetMapping("/print/{id}")
    public ApiRespond printOrder(@PathVariable Long id){

       ApiRespond status = new ApiRespond().setStatus(orderService.printOrder(id));
       return status;
    }

    @PostMapping("/close/{id}")
    public ApiRespond closeOrder(@PathVariable Long id){
        ApiRespond status = new ApiRespond().setStatus(orderService.closeOrder(id));
        //System.out.println(status);
        return status;

    }

}

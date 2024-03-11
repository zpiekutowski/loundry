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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    private Order order;

    @CrossOrigin
    @PostMapping("/add_unit")
    public Order addService(@RequestBody UnitOrderDTO unitOrderDTO) throws IdServedUnitOutOfBoundException {
        return orderService.newUnitOrder(unitOrderDTO);
    }


    @PostMapping("/setcustomer/{id}")
    public Order setCustomerId(@PathVariable Long id) throws IdCustomerOutOfBoudException {
        return orderService.setCustomerId(id);
    }
   // @CrossOrigin
    @PostMapping("/new")
    public Order newOrder() throws OrderExistException {
        return orderService.newOrder();
       }

   // @CrossOrigin
    @PostMapping("/cancel_order")
    public void cancelOrder(){
        orderService.cancelOrder();
    }


    @CrossOrigin
    @GetMapping("/current")
    public ResponseEntity<Order> getcurrent(){
        Order order =  orderService.getCurrent();
        if (order == null){
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(order,HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/save")   //save order to DB
    public Order save(){
        return orderService.saveOrder();
    }

    @CrossOrigin
    @GetMapping("/read/{id}")
    public Order readOrder(@PathVariable Long id){
        return orderService.read(id);
    }


}

package com.zbiir.loundry.service;

import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.OrderDTO;
import com.zbiir.loundry.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public List<OrderDTO> getActiveOrders() {

        List<OrderDTO> ordersDTO = new ArrayList<>();
        List<Order> orders = orderRepository.findAllOrders();
        //List<Order> orders = orderRepository.findAll();
        orders.forEach((item)->{
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(item.getId());
            orderDTO.setCustomerName(item.getCustomer().getName());
            orderDTO.setStartingDate(item.getStartDate());
            orderDTO.setPlanedFinishDate(item.getPlanedFinishDate());
            orderDTO.setUnitQtn(item.getUnitOrders().size());
            orderDTO.setPrice(item.getPrice());
            orderDTO.setReady(item.getIsReady());
            ordersDTO.add(orderDTO);
        });
        return ordersDTO;
    }

    public Order getOrderbyId(long id) {
       Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            return order.get();
        }
        else
            return null;
    }

    public List<Order> getAllAllOrders() {
        return orderRepository.findAll();

    }


}

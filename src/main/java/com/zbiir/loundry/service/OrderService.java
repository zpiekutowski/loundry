package com.zbiir.loundry.service;

import com.zbiir.loundry.exception.OrderActiveException;
import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.OrderArchive;
import com.zbiir.loundry.model.OrderDTO;
import com.zbiir.loundry.repositories.OrderArchiveRepository;
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
    @Autowired
    private PrintService printService;
    @Autowired
    private OrderArchiveRepository orderArchiveRepository;


    public List<OrderDTO> getActiveOrders() {

        List<OrderDTO> ordersDTO = new ArrayList<>();
        List<Order> orders = orderRepository.findAllOrders();
        //List<Order> orders = orderRepository.findAll();
        orders.forEach((item) -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(item.getId());
            orderDTO.setCustomerName(item.getCustomer().getName());
            orderDTO.setCustomerId(item.getCustomer().getId());
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
        if (order.isPresent()) {
            return order.get();

        } else
            return null;
    }

    public List<Order> getAllAllOrders() {
        return orderRepository.findAll();

    }

    public boolean printOrder(Long id) throws OrderExistException {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new OrderExistException("Zamowienie nie istnieje");
        }
        return printService.printOrder(order.get(), 1, false);
        //return true;
    }

    public Boolean closeOrder(Long id) throws OrderExistException, OrderActiveException {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new OrderExistException("Zamowienie nie istnieje");
        } else {
            Order order = optionalOrder.get();
            if(!order.getIsReady())
                throw new OrderActiveException("Blad zamkniecia - zamowienie niegotowe");
            OrderArchive orderArchive = new OrderArchive(order);
            orderArchiveRepository.save(orderArchive);
            orderRepository.delete(order);
            return true;
        }
    }


    public List<Order> getOrdersByCustomerId(Long id) {
        return orderRepository.findOrdersByCustomerId(id);
    }
}

package com.zbiir.loundry.service;


import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.IdServedUnitOutOfBoundException;
import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.UnitOrder;
import com.zbiir.loundry.model.UnitOrderDTO;
import com.zbiir.loundry.repositories.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ServedUnitService servedUnitService;
    @Autowired
    HttpSession session;

    private Order order;
    public Order newOrder() throws OrderExistException{
        Order order = (Order) session.getAttribute("order");
        if (order==null){
            order = new Order();
            session.setAttribute("order", order);
            order.setStartDate(LocalDate.now());
            return order;
        }
        else{
            throw new OrderExistException("New order can NOT be placed, please close current order");
        }

    }


    public Order setCustomerId(Long id) throws IdCustomerOutOfBoudException {
        order = (Order) session.getAttribute("order");
        order.setCustomer(customerService.getSingleCustomer(id));
        return order;
    }

    public String test() {
        return session.getId();
    }

    public Order newUnitOrder(UnitOrderDTO unitOrderDTO) throws IdServedUnitOutOfBoundException {

        Order order = (Order) session.getAttribute("order");

        UnitOrder unitOrder = new UnitOrder();
        unitOrder.setType(servedUnitService.getServedUnit(unitOrderDTO.getIdServedUnit()));
        unitOrder.setTagLabel(unitOrderDTO.getTag());
        unitOrder.setComment(unitOrderDTO.getComments());
        unitOrder.setUnitPrice(unitOrderDTO.getUnitPrice());
        unitOrder.setStartDate(LocalDate.now());
        unitOrder.setIdOrder(order);
        order.getUnitOrders().add(unitOrder);
        order.setPrice((float) order.getUnitOrders().stream().map(UnitOrder::getUnitPrice).reduce(0F,(a,b)->a+b));

        return order;
    }

    public Order saveOrder() {

        order =(Order) session.getAttribute("order");
        if (order.getCustomer()!=null && !(order.getUnitOrders().isEmpty())){
            orderRepository.save(order);
            return order;
        }else{
            System.out.println("dodaj validacje zamowienia");
            return null;
        }


    }
}

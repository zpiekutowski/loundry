package com.zbiir.loundry.service;


import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.IdServedUnitOutOfBoundException;
import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.*;
import com.zbiir.loundry.repositories.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NewOrderService {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private ServedUnitService servedUnitService;
    @Autowired
    HttpSession session;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PrintService printService;

    private NewOrder newOrder;



    public NewOrder newOrder() throws OrderExistException{
        NewOrder newOrder = (NewOrder) session.getAttribute("newOrder");
        if (newOrder==null){
            newOrder = new NewOrder();
            newOrder.setPrice(0.00f);
            newOrder.setIsPaid(false);
            newOrder.setPlanedFinishDate(LocalDate.now().plusDays(7));
            newOrder.setStartDate(LocalDate.now());
            session.setAttribute("newOrder", newOrder);
            return newOrder;
        }
        else{
            throw new OrderExistException("New order can NOT be placed, please close current");
        }

    }


    public NewOrder setCustomerId(Long id) throws IdCustomerOutOfBoudException {
        newOrder = (NewOrder) session.getAttribute("newOrder");
        newOrder.setCustomer(customerService.getSingleCustomer(id));
        return newOrder;
    }


    public NewOrder newUnitOrder(UnitOrderDTO unitOrderDTO) throws IdServedUnitOutOfBoundException {

        newOrder = (NewOrder) session.getAttribute("newOrder");
        unitOrderDTO.setRowNumber(newOrder.getUnitOrders().size() +1);
        newOrder.getUnitOrders().add(unitOrderDTO);
        newOrder.setPrice((float) newOrder.getUnitOrders().stream().map(UnitOrderDTO::getPrice).reduce(0F,(a,b)->a+b));

        return newOrder;
    }



    public void cancelOrder() {
        NewOrder orderDTO = (NewOrder) session.getAttribute("newOrder");
        if(orderDTO != null){
            session.removeAttribute("newOrder");
        }

    }

    public NewOrder getCurrent() {
        newOrder = (NewOrder) session.getAttribute("newOrder");
        return newOrder;
    }

    public NewOrder removeUnitOrder(int id) {
        NewOrder newOrder = (NewOrder) session.getAttribute("newOrder");
        newOrder.getUnitOrders().remove(id-1);

        for(int i=0;i<newOrder.getUnitOrders().size();i++){
            newOrder.getUnitOrders().get(i).setRowNumber(i+1);
        };
        newOrder.setPrice((float) newOrder.getUnitOrders().stream().map(UnitOrderDTO::getPrice).reduce(0F,(a,b)->a+b));
        return newOrder;
    }

    public UnitOrderDTO getUnitOrderDTO(int idRow) {
        newOrder = (NewOrder) session.getAttribute("newOrder");
        return newOrder.getUnitOrders().get(idRow-1);
    }

    public void editUnitOrderDTO(UnitOrderDTO unitOrderDTO) {
        newOrder = (NewOrder) session.getAttribute("newOrder");
        newOrder.getUnitOrders().set(unitOrderDTO.getRowNumber()-1,unitOrderDTO);
        newOrder.setPrice((float) newOrder.getUnitOrders().stream().map(UnitOrderDTO::getPrice).reduce(0F,(a,b)->a+b));

    }

    public void setPlanedFinishDate(Date date) {
        newOrder = (NewOrder) session.getAttribute("newOrder");
        newOrder.setPlanedFinishDate(date.getDate());
    }

    public void setIsPaid(boolean isPaid) {
        newOrder = (NewOrder) session.getAttribute("newOrder");
        newOrder.setIsPaid(isPaid);
    }

    public void saveOrder() {

        newOrder = (NewOrder) session.getAttribute("newOrder");
        Order order = new Order();

        order.setCustomer(newOrder.getCustomer());
        order.setPrice(newOrder.getPrice());
        order.setStartDate(newOrder.getStartDate());
        order.setPlanedFinishDate(newOrder.getPlanedFinishDate());
        order.setIsReady(false);
        order.setIsPaid(newOrder.getIsPaid());

        newOrder.getUnitOrders().forEach((unit)->{
            UnitOrder unitOrder = new UnitOrder();
            unitOrder.setIdOrder(order);
            unitOrder.setType(unit.getType());
            unitOrder.setTagLabel(unit.getTag());
            unitOrder.setComment(unit.getComment());
            unitOrder.setUnitPrice(unit.getPrice());
            unitOrder.setFinishDate(null);
            order.getUnitOrders().add(unitOrder);
        });

        orderRepository.save(order);
        printService.printOrder(order,2);
        session.removeAttribute("newOrder");

    }
}

package com.zbiir.loundry.service;

import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.UnitOrderExistException;
import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.UnitOrder;
import com.zbiir.loundry.model.UnitOrderViewDTO;
import com.zbiir.loundry.repositories.OrderRepository;
import com.zbiir.loundry.repositories.UnitOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitOrderService {
    @Autowired
    UnitOrderRepository unitOrderRepository;
    @Autowired
    OrderRepository orderRepository;


    public UnitOrder getUnitOrder(Long id) throws IdCustomerOutOfBoudException {
       Optional<UnitOrder> respond =  unitOrderRepository.findById(id);
       if(respond.isPresent()){
           return respond.get();
       }
       else {
           throw new IdCustomerOutOfBoudException("Brak zamowienia w bazie danych:" + id);
       }

    }

    public void saveUnitOrder(UnitOrder unitOrder) throws UnitOrderExistException {
        UnitOrder unitOrderOrginal;
        Optional<UnitOrder> optionalUnitOrderOrginal = unitOrderRepository.findById(unitOrder.getId());
         if(optionalUnitOrderOrginal.isEmpty()) {
             throw new UnitOrderExistException("ID unit order dont exist");
         } else{unitOrderOrginal = optionalUnitOrderOrginal.get();}

        unitOrderOrginal.setUnitPrice(unitOrder.getUnitPrice());
        unitOrderOrginal.setType(unitOrder.getType());
        unitOrderOrginal.setComment(unitOrder.getComment());
        unitOrderOrginal.setTagLabel(unitOrder.getTagLabel());
        unitOrderOrginal.setTagLabelNo(unitOrder.getTagLabelNo());
        unitOrderRepository.save(unitOrderOrginal);
        Order order = unitOrderOrginal.getIdOrder();
        order.setPrice(order.getUnitOrders().stream().map(UnitOrder :: getUnitPrice).reduce(0F,(a,b)-> a+b));
        orderRepository.save(order);
    }

    public void setFinishDate(Long id) throws UnitOrderExistException {
        UnitOrder unitOrder;
        Optional<UnitOrder> optionalUnitOrder = unitOrderRepository.findById(id);
        if(optionalUnitOrder.isEmpty()) {
            throw new UnitOrderExistException("ID unit order dont exist");
        } else{unitOrder = optionalUnitOrder.get();}

        unitOrder.setFinishDate(LocalDate.now());
        unitOrderRepository.save(unitOrder);
        Order order = unitOrder.getIdOrder();
        if(order.getUnitOrders().stream().map(n -> n.getFinishDate()).filter(n -> n == null).count() == 0){
            order.setIsReady(true);
            orderRepository.save(order);
        }


    }

    public List<UnitOrderViewDTO> getUnitOrdersView() {
        //List<UnitOrder> unitsOrder =  unitOrderRepository.findAll();
        List<UnitOrder> unitsOrder =  unitOrderRepository.findAllUnitOrders();

        List<UnitOrderViewDTO> unitOrdersView = new ArrayList<UnitOrderViewDTO>();
        unitsOrder.forEach((n)->{
            UnitOrderViewDTO unitOrderViewDTO = new UnitOrderViewDTO();
            unitOrderViewDTO.setId(n.getId());
            unitOrderViewDTO.setType(n.getType());
            unitOrderViewDTO.setTagLabel(n.getTagLabel());
            unitOrderViewDTO.setTagLabelNo(n.getTagLabelNo());
            unitOrderViewDTO.setComment(n.getComment());
            unitOrderViewDTO.setPrice(n.getUnitPrice());
            unitOrderViewDTO.setFinishDate(n.getFinishDate());
            unitOrderViewDTO.setPickUpDate(n.getIdOrder().getPlanedFinishDate());
            unitOrdersView.add(unitOrderViewDTO);
        });
        return unitOrdersView;

    }
}











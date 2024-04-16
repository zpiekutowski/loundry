package com.zbiir.loundry.controller;

import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.IdServedUnitOutOfBoundException;
import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.Date;
import com.zbiir.loundry.model.NewOrder;
import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.UnitOrderDTO;
import com.zbiir.loundry.service.NewOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("new_order")
public class NewOrderController {

    @Autowired
    private NewOrderService newOrderService;
    private NewOrder orderDTO;

    @PostMapping("/new")
    public NewOrder openNewOrder() throws OrderExistException {
        return newOrderService.newOrder();
    }

    @PostMapping("/add_unit")
    public NewOrder addService(@Valid @RequestBody UnitOrderDTO unitOrderDTO) throws IdServedUnitOutOfBoundException {
        return newOrderService.newUnitOrder(unitOrderDTO);
    }


    @PostMapping("/setcustomer/{id}")
    public NewOrder setCustomerId(@PathVariable Long id) throws IdCustomerOutOfBoudException {
        return newOrderService.setCustomerId(id);
    }

    // @CrossOrigin
    @PostMapping("/cancel_order")
    public void cancelOrder() {
        newOrderService.cancelOrder();
    }

    @PostMapping("/remove_unit/{id}")
    public NewOrder removeUnitOrder(@PathVariable int id) {
        return newOrderService.removeUnitOrder(id);

    }


    @GetMapping("/current")
    public ResponseEntity<Order> getcurrent() {
        NewOrder newOrder = newOrderService.getCurrent();
        if (newOrder == null) {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(newOrder, HttpStatus.OK);
    }

    @GetMapping("/get_unit_order_DTO/{idRow}")
    public ResponseEntity<UnitOrderDTO> getUnirOrderDTO(@PathVariable int idRow) {
        return new ResponseEntity(newOrderService.getUnitOrderDTO(idRow), HttpStatus.OK);
    }

    @PostMapping("/edit_unit_order_DTO")
    public void editUnitOrderDTO(@Valid @RequestBody UnitOrderDTO unitOrderDTO) {
        newOrderService.editUnitOrderDTO(unitOrderDTO);
    }

    @PostMapping("/set_planed_finish_date")
    public void setPlanedFinishDate(@RequestBody Date date) {
        newOrderService.setPlanedFinishDate(date);
    }

    @PostMapping("/set_is_paid/{isPaid}")
    public void setIsPaid(@PathVariable boolean isPaid) {

        newOrderService.setIsPaid(isPaid);
    }

    @PostMapping("/save_order")
    public void saveOrder(@RequestParam("print") Boolean print) {
        newOrderService.saveOrder(print);
    }

}

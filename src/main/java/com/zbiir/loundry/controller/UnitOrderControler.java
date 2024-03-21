package com.zbiir.loundry.controller;

import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.UnitOrderExistException;
import com.zbiir.loundry.model.UnitOrder;
import com.zbiir.loundry.model.UnitOrderViewDTO;
import com.zbiir.loundry.service.UnitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit_order")
public class UnitOrderControler {

    @Autowired
    UnitOrderService unitOrderService;

    @GetMapping("/{id}")
    public ResponseEntity getUnitOrder(@PathVariable Long id) throws IdCustomerOutOfBoudException {
        return new ResponseEntity(unitOrderService.getUnitOrder(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public void saveUnitorder(@RequestBody UnitOrder unitOrder) throws UnitOrderExistException {

        unitOrderService.saveUnitOrder(unitOrder);

    }
    @PostMapping("/set_finish_date/{id}")
    public void setFinishDate(@PathVariable Long id) throws UnitOrderExistException {
        unitOrderService.setFinishDate(id);
    }

    @GetMapping("/all")
    public List<UnitOrderViewDTO> getAllUnitsOrder(){
        return unitOrderService.getUnitOrdersView();

    }

}

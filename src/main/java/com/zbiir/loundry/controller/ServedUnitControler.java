package com.zbiir.loundry.controller;

import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.IdServedUnitOutOfBoundException;
import com.zbiir.loundry.model.ServedUnit;
import com.zbiir.loundry.model.ServedUnitDTO;
import com.zbiir.loundry.service.ServedUnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sunit")
public class ServedUnitControler {
    @Autowired
    private ServedUnitService servedUnitService;

    @GetMapping("all")
    public List<ServedUnit> getAllServedUnit(){
        return servedUnitService.getAllServedUnit();
    }

    @PostMapping("add")
    public ServedUnit addServedUnit(@Valid @RequestBody ServedUnitDTO servedUnitDTO){
        ServedUnit servedUnit = new ServedUnit(
                null,
                servedUnitDTO.getDescryption()
        );
        return servedUnitService.addServedUnit(servedUnit);
    }

    @GetMapping("/{id}")
    public ServedUnit getServedUnit (@PathVariable long id) throws IdServedUnitOutOfBoundException {
         return servedUnitService.getServedUnit(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteServedUnit(@PathVariable long id){
        servedUnitService.deleteServedUnit(id);
    }
    @PutMapping("update/{id}")
    public ServedUnit udateServedUnit(@Valid @RequestBody ServedUnitDTO servedUnitDTO,@PathVariable long id){
        ServedUnit servedUnit = new ServedUnit(
                id,
                servedUnitDTO.getDescryption()
        );
        return servedUnitService.updateServedUnit(servedUnit);

    }

}

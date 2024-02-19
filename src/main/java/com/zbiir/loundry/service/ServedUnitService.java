package com.zbiir.loundry.service;

import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.IdServedUnitOutOfBoundException;
import com.zbiir.loundry.model.ServedUnit;
import com.zbiir.loundry.repositories.ServedUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServedUnitService {
    @Autowired
    private ServedUnitRepository servedUnitRepository;

    public List<ServedUnit> getAllServedUnit(){
        return servedUnitRepository.findAll();
    }

    public ServedUnit addServedUnit(ServedUnit servedUnit) {
        return servedUnitRepository.save(servedUnit);
    }

    public ServedUnit getServedUnit(long id) throws IdServedUnitOutOfBoundException {
        Optional<ServedUnit> servedUnit = servedUnitRepository.findById(id);
        if(servedUnit.isPresent()){
            return servedUnit.get();
        }else {throw new IdServedUnitOutOfBoundException("Nie ma takiej usługi: "+id);}
    }

    public void deleteServedUnit(long id) {
        servedUnitRepository.deleteById(id);
    }

    public ServedUnit updateServedUnit(ServedUnit servedUnit) {
        return servedUnitRepository.save(servedUnit);
    }
}

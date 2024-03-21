package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.UnitOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitOrderRepository extends JpaRepository<UnitOrder, Long> {

    @Query("Select o From UnitOrder o left join fetch o.idOrder left join fetch o.type")
    List<UnitOrder> findAllUnitOrders();
}

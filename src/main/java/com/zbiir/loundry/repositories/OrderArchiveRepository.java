package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.OrderArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderArchiveRepository extends JpaRepository <OrderArchive,Long> {

    @Query("Select o From OrderArchive o Where o.customer.id=?1")
     List<OrderArchive> findOrderArchiveByCustomerId(long id);
}

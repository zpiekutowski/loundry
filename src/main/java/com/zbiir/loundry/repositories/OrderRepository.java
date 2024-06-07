package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("Select o From Order o left join fetch o.unitOrders  left join fetch o.customer")
    List<Order> findAllOrders();

    @Query("Select o From Order o Where o.customer.id=?1")
    List<Order> findOrdersByCustomerId(Long id);
}

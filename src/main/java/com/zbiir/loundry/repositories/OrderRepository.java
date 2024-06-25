package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("Select o From Order o left join fetch o.unitOrders  left join fetch o.customer")
    List<Order> findAllOrders();

    @Query("Select o From Order o Where o.customer.id=?1")
    List<Order> findOrdersByCustomerId(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orders (id, id_customer, price, start_date, planed_finish_date, is_ready, is_paid) " +
            "VALUES (:id, :customer, :price, :startDate, :planedFinishDate, :isReady, :isPaid)",
            nativeQuery = true)
    void restore(@Param("id") Long id,
                 @Param("customer") Long customer,
                 @Param("price") Float price,
                 @Param("startDate") LocalDate startDate,
                 @Param("planedFinishDate") LocalDate planedFinishDate,
                 @Param("isReady") Boolean isReady,
                 @Param("isPaid") Boolean isPaid
    );

}

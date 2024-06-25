package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.UnitOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface UnitOrderRepository extends JpaRepository<UnitOrder, Long> {

    @Query("Select o From UnitOrder o left join fetch o.idOrder left join fetch o.type")
    List<UnitOrder> findAllUnitOrders();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO unit_order (id, id_order, served_unit, tag_label, tag_label_no, comment, unit_price, finish_date) " +
            "VALUES (:id, :idOrder, :type, :tagLabel, :tagLabelNo, :comment, :unitPrice, :finishDate)",
            nativeQuery = true)
    void restore(@Param("id") Long id,
                 @Param("idOrder") Long idOrder,
                 @Param("type") Long type,
                 @Param("tagLabel") String tagLabel,
                 @Param("tagLabelNo") String tagLabelNo,
                 @Param("comment") String comment,
                 @Param("unitPrice") Float unitPrice,
                 @Param("finishDate") LocalDate finishDate
                 );

}

package com.zbiir.loundry.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Float price;
    private LocalDate startDate;
    private LocalDate finishDate;
    @OneToMany (mappedBy = "idOrder")
    private List<UnitOrder> unitOrders;


}

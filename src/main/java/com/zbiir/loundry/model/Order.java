package com.zbiir.loundry.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER")
    private Customer customer;
    private Float price;
    private LocalDate startDate;
    private LocalDate finishDate;
    @OneToMany (mappedBy = "idOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UnitOrder> unitOrders = new ArrayList<UnitOrder>();


}

package com.zbiir.loundry.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.PrivilegedAction;
import java.time.LocalDate;

@Data
@Entity
public class UnitOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "id_order")
    private Order idOrder;
    @ManyToOne
    @JoinColumn (name = "served_unit")
    private ServedUnit type;
    private String tagLabel;
    private String comment;
    private Float unitPrice;
    private LocalDate startDate;
    private LocalDate finishDate;

}

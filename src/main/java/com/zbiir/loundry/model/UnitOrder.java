package com.zbiir.loundry.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivilegedAction;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UnitOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "id_order")
    @JsonBackReference
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

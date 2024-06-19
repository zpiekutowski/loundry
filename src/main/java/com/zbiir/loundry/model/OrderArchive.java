package com.zbiir.loundry.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
//@Builder
public class OrderArchive {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER")
    private Customer customer;
    private Float price;
    private LocalDate startDate;
    private LocalDate planedFinishDate;
    private Boolean isPaid;
    private  LocalDate pickupDate;
    @OneToMany (mappedBy = "idOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UnitOrderArchive> unitOrdersArchive = new ArrayList<UnitOrderArchive>();

    public OrderArchive(Order order){
        this.setId(order.getId());
        this.setCustomer(order.getCustomer());
        this.setPrice(order.getPrice());
        this.setStartDate(order.getStartDate());
        this.setPlanedFinishDate(order.getPlanedFinishDate());
        this.setIsPaid(order.getIsPaid());
        this.setPickupDate(LocalDate.now());
        order.getUnitOrders().forEach((n)->{
            UnitOrderArchive uoa = new UnitOrderArchive(n);
            uoa.setIdOrder(this);
            this.unitOrdersArchive.add(uoa);
        });
    }

//    @Override
//    public String toString() {
//        return "OrderArchive{" +
//                "id=" + id +
//                ", customer=" + customer +
//                ", price=" + price +
//                ", startDate=" + startDate +
//                ", planedFinishDate=" + planedFinishDate +
//                ", pickupDate=" + pickupDate +
//                ", unitOrdersArchive=" + unitOrdersArchive +
//                '}';
//    }
}

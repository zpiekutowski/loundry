package com.zbiir.loundry.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
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
    private LocalDate planedFinishDate;
    private Boolean isReady;
    private Boolean isPaid;
    @OneToMany (mappedBy = "idOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UnitOrder> unitOrders = new ArrayList<UnitOrder>();

//    public Order(OrderArchive orderArchive){
//        this.setId(orderArchive.getId());
//        this.setCustomer(orderArchive.getCustomer());
//        this.setPrice(orderArchive.getPrice());
//        this.setStartDate(orderArchive.getStartDate());
//        this.setPlanedFinishDate(orderArchive.getPlanedFinishDate());
//        this.setIsReady(true);
//        this.setIsPaid(orderArchive.getIsPaid());
//        orderArchive.getUnitOrdersArchive().forEach(n->{
//            UnitOrder uo = new UnitOrder(n);
//            uo.setIdOrder(this);
//            unitOrders.add(uo);
//        });
//    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", price=" + price +
                ", startDate=" + startDate +
                ", finishDate=" + planedFinishDate +
                ", isReady=" + isReady +
                ", isPaid=" + isPaid +
                ", unitOrders=" + unitOrders +
                '}';
    }
}

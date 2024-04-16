package com.zbiir.loundry.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderArchiveDTO {
    private Long id;
    private String customer;
    private Float price;
    private LocalDate startDate;
    private LocalDate pickupDate;

    public OrderArchiveDTO(OrderArchive orderArchive) {
        this.id = orderArchive.getId();
        this.customer = orderArchive.getCustomer().getName();
        this.price = orderArchive.getPrice();
        this.startDate = orderArchive.getStartDate();
        this.pickupDate = orderArchive.getPickupDate();
    }

}

package com.zbiir.loundry.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
public class NewOrder {
    private Customer customer;
    private Float price;
    private LocalDate startDate;
    private LocalDate planedFinishDate;
    private Boolean isPaid;
    private List<UnitOrderDTO> unitOrders = new ArrayList<UnitOrderDTO>();


}

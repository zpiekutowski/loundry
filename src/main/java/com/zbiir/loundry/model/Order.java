package com.zbiir.loundry.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private Float price;
    private LocalDate startDate;
    private LocalDate finishDate;
    private List<UnitOrder> unitOrders;

}

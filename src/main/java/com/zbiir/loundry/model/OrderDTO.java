package com.zbiir.loundry.model;

import lombok.Data;

import java.time.LocalDate;


@Data
public class OrderDTO {
    private long id;
    private String customerName;
    private int unitQtn;
    private LocalDate startingDate;
    private LocalDate planedFinishDate;
    private float price;
    private boolean isReady;


}

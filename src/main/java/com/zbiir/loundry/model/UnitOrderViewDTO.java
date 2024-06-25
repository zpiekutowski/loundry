package com.zbiir.loundry.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UnitOrderViewDTO {
    private Long id;
    private ServedUnit type;
    private String tagLabel;
    private String tagLabelNo;
    private String comment;
    private Float price;
    private LocalDate finishDate;
    private LocalDate pickUpDate;
    private Long orderId;

}

package com.zbiir.loundry.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UnitOrderDTO {
    private Long idType;
    private String tag;
    private String comment;
    private Float price;
    private LocalDate plannedFinishDate;

}

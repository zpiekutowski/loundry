package com.zbiir.loundry.model;

import lombok.Data;

@Data
public class UnitOrderDTO {
    private Long idServedUnit;
    private String tag;
    private String comments;
    private Float unitPrice;
}

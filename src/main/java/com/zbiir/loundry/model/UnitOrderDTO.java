package com.zbiir.loundry.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UnitOrderDTO {
    private int rowNumber;
    private ServedUnit type;
    private String tag;
    private String comment;
    @Valid
    @NotNull (message = "Price cannot be null")
    private Float price;


}

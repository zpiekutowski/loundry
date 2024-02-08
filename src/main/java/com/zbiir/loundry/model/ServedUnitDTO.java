package com.zbiir.loundry.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ServedUnitDTO {

    @Valid
    @NotEmpty(message = "Usluga musi zawierac opis")
    @NotBlank(message = "Usluga musi zawierac opis")
    private String  descryption;

}

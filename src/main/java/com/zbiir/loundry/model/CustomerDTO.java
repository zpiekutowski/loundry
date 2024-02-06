package com.zbiir.loundry.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
public class CustomerDTO {
        @Valid
        @NotNull(message = "Name is mandatory")
        @NotEmpty(message = "Name can not be empty")
        private String name;
        private String addres;
        private String phone;
}

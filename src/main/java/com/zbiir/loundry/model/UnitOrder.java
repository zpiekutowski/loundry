package com.zbiir.loundry.model;

import java.security.PrivilegedAction;
import java.time.LocalDate;

public class UnitOrder {

    private Long id;
    private Long idOrder;
    private ServedUnit type;
    private String tagLabel;
    private Float unitPrice;
    private LocalDate startDate;
    private LocalDate finishDate;

}

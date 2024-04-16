package com.zbiir.loundry.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
//@Builder
@NoArgsConstructor
public class UnitOrderArchive {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_order")
    @JsonBackReference
    private OrderArchive idOrder;
    @ManyToOne
    @JoinColumn (name = "served_unit")
    private ServedUnit type;
    private String tagLabel;
    private String comment;
    private Float unitPrice;
    private LocalDate finishDate;

    public UnitOrderArchive(UnitOrder unitOrder){
        this.setId(unitOrder.getId());
//        this.setIdOrder(unitOrder.getIdOrder()); //set in OrderArchiveService
        this.setType(unitOrder.getType());
        this.setTagLabel(unitOrder.getTagLabel());
        this.setComment(unitOrder.getComment());
        this.setUnitPrice(unitOrder.getUnitPrice());
        this.setFinishDate(unitOrder.getFinishDate());
    }

    @Override
    public String toString() {
        return "UnitOrderArchive{" +
                "id=" + id +
//                ", idOrder=" + idOrder +
                ", type=" + type +
                ", tagLabel='" + tagLabel + '\'' +
                ", comment='" + comment + '\'' +
                ", unitPrice=" + unitPrice +
                ", finishDate=" + finishDate +
                '}';
    }
}

package com.zbiir.loundry.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivilegedAction;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UnitOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "id_order")
    @JsonBackReference
    private Order idOrder;
    @ManyToOne
    @JoinColumn (name = "served_unit")
    private ServedUnit type;
    private String tagLabel;        // main laebl id
    private String tagLabelNo;      // subnumber  label
    private String comment;
    private Float unitPrice;
    private LocalDate finishDate;


//    public UnitOrder(UnitOrderArchive unitOrderArchive){
//        this.setId(unitOrderArchive.getId());
//        this.setType(unitOrderArchive.getType());
//        this.setTagLabel(unitOrderArchive.getTagLabel());
//        this.setTagLabelNo(unitOrderArchive.getTagLabelNo());
//        this.setComment(unitOrderArchive.getComment());
//        this.setUnitPrice(unitOrderArchive.getUnitPrice());
//        this.setFinishDate(unitOrderArchive.getFinishDate());
//    }



    @Override
    public String toString() {
        return "UnitOrder{" +
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

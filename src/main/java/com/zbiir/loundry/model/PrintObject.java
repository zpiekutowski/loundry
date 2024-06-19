package com.zbiir.loundry.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintObject {
//    private String lp;
    private String idUnit;
    private String type;
    private String tagLabel;
    private String tagLabelNo;
    private String comment;
    private String price;

    @Override
    public String toString() {
        return "PrintObject{" +
//                "lp='" + lp + '\'' +
                " idUnit='" + idUnit + '\'' +
                ", type='" + type + '\'' +
                ", tagLabel='" + tagLabel + '\'' +
                ", comment='" + comment + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

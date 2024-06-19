package com.zbiir.loundry.service;

import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.PrintObject;
import com.zbiir.loundry.model.UnitOrder;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrintService {

    @Value("${pinter.name}")
    private String printerName;


    public boolean printOrder(Order order, Integer quantity, Boolean printPDF) {


        try {
            Map<String, Object> dataPrint = new HashMap<>();
            dataPrint.put("startDate", order.getStartDate().toString());
            dataPrint.put("finishDate", order.getPlanedFinishDate().toString());
            dataPrint.put("name", order.getCustomer().getName());
            dataPrint.put("addres", order.getCustomer().getAddres());
            dataPrint.put("phone", order.getCustomer().getPhone());
            dataPrint.put("idOrder", order.getId().toString());
            String paid = (order.getIsPaid()) ? "ZAPŁACONE" : "PŁATNE PRZY ODBIORZE";
            dataPrint.put("isPaid", paid);
            dataPrint.put("totalPrice", String.format("%.2f", order.getPrice()));


            List <PrintObject> printUnitOrders = new ArrayList<PrintObject>();

            order.getUnitOrders().forEach(n->{
                PrintObject printObject = new PrintObject();
                printObject.setType(n.getType().getId().toString());
                printObject.setIdUnit(n.getId().toString());
                printObject.setTagLabel(n.getTagLabel());
                printObject.setTagLabelNo(n.getTagLabelNo());
                printObject.setComment(n.getComment());
                printObject.setPrice(String.format("%.2f", n.getUnitPrice()));
                printUnitOrders.add(printObject);

            });

//            for (int i = 0; i < order.getUnitOrders().size(); i++) {
//                PrintObject printObject = new PrintObject();
//                UnitOrder unitOrder = order.getUnitOrders().get(i);
//                printObject.setType(unitOrder.getType().getId().toString());
//                printObject.setIdUnit(unitOrder.getId().toString());
//                printObject.setTagLabel(unitOrder.getTagLabel());
//                printObject.setComment(unitOrder.getComment());
//                printObject.setPrice(String.format("%.2f", unitOrder.getUnitPrice()));
//                printUnitOrders.add(printObject);
//
//            }


            JRBeanCollectionDataSource tableData = new JRBeanCollectionDataSource(printUnitOrders);


            File file = ResourceUtils.getFile("classpath:printForm.jrxml");
            JasperReport compileReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, dataPrint, tableData);

            if (printPDF)
                JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/pralnia/zamowienia/" + order.getId().toString() + ".pdf");

            if (quantity > 0) {
                PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                printRequestAttributeSet.add(MediaSizeName.ISO_A5);
                printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
                printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 148, 210, MediaPrintableArea.MM));
                printRequestAttributeSet.add(new Copies(1));

                PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
                printServiceAttributeSet.add(new PrinterName(printerName, null));


                JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
                configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
                configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
                configuration.setDisplayPageDialog(false);
                configuration.setDisplayPrintDialog(false);
                exporter.setConfiguration(configuration);
                while (quantity > 0) {
                    exporter.exportReport();
                    quantity--;
                }
            }
            return true;
        } catch (FileNotFoundException | JRException ex) {
            System.out.println("Print" + ex.getMessage());
            return false;
        }

    }

}

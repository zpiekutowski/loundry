package com.zbiir.loundry.service;

import com.zbiir.loundry.model.Order;
import com.zbiir.loundry.model.PrintObject;
import com.zbiir.loundry.model.UnitOrder;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.springframework.stereotype.Service;

import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrintService {

    public boolean printOrder(Order order, Integer quantity) {

        try {
            Map<String, Object> dataPrint = new HashMap<>();
            dataPrint.put("startDate", order.getStartDate().toString());
            dataPrint.put("finishDate", order.getPlanedFinishDate().toString());
            dataPrint.put("name", order.getCustomer().getName());
            dataPrint.put("addres", order.getCustomer().getAddres());
            dataPrint.put("phone", order.getCustomer().getPhone());
            dataPrint.put("idOrder", order.getId().toString());
            String paid = (order.getIsPaid()) ? "ZAPLACONE" : "PLATNE PRZY ODBIORZE";
            dataPrint.put("isPaid", paid);
            dataPrint.put("totalPrice", String.format("%.2f", order.getPrice()));


            List printUnitOrders = new ArrayList();
            for (int i = 0; i < order.getUnitOrders().size(); i++) {
                PrintObject printObject = new PrintObject();
                UnitOrder unitOrder = order.getUnitOrders().get(i);
                printObject.setLp(Integer.toString(i + 1));
                printObject.setType(unitOrder.getType().getId().toString());
                printObject.setIdUnit(unitOrder.getId().toString());
                printObject.setTagLabel(unitOrder.getTagLabel());
                printObject.setComment(unitOrder.getComment());
                printObject.setPrice(String.format("%.2f", unitOrder.getUnitPrice()));
                printUnitOrders.add(printObject);

            }


            JRBeanCollectionDataSource tableData = new JRBeanCollectionDataSource(printUnitOrders);

            JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/printForm.jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, dataPrint, tableData);

            JasperExportManager.exportReportToPdfFile(jasperPrint, order.getId().toString() + ".pdf");

            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(MediaSizeName.ISO_A5);
            printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
            printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 148, 210, MediaPrintableArea.MM));
            printRequestAttributeSet.add(new Copies(1));

            PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
            printServiceAttributeSet.add(new PrinterName(
                    PrintServiceLookup.lookupDefaultPrintService().getName(), null));


            JRPrintServiceExporter exporter = new JRPrintServiceExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
            configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
            configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
            configuration.setDisplayPageDialog(false);
            configuration.setDisplayPrintDialog(false);
            exporter.setConfiguration(configuration);
            while (quantity > 0) {
                System.out.println("Drukowanie zamowienia");
                exporter.exportReport();
                quantity--;
            }
            return true;
        } catch (FileNotFoundException | JRException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

}

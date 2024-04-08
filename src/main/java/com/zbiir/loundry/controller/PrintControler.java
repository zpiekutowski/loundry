package com.zbiir.loundry.controller;

import com.zbiir.loundry.model.PrintObject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.PrintException;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/print")
public class PrintControler {
    @GetMapping("/order")
    public String printOrder() throws FileNotFoundException, JRException{

        List unitOrders  = new ArrayList();
//        PrintObject p1 = new PrintObject("1");
//        PrintObject p2 = new PrintObject("2");
//        PrintObject p3 = new PrintObject("3");


        PrintObject p1 = new PrintObject("1","23","1","875",
                "Koszula czarna poplaminona w biale plamy jak damy rade to bedzie ok jak nie to do odbioru a jak nie to do wyrzucenia do smieci","80.00");
        PrintObject p2 = new PrintObject("2","25","4","950","Dywan","120.00");
        PrintObject p3 = new PrintObject("3","30","7","123","Sukienka","60.00");

        unitOrders.add(p1);
        unitOrders.add(p2);
        unitOrders.add(p3);
        JRBeanCollectionDataSource tableData = new JRBeanCollectionDataSource(unitOrders);

        Map<String, Object> dataPrint = new HashMap<>();
        dataPrint.put("startDate","12-03-2024");
        dataPrint.put("finishDate","19-03-2024");
        dataPrint.put("name","Teresa Tracz");
        dataPrint.put("addres","Kwiatowa 23");
        dataPrint.put("phone","159 256 369");
        dataPrint.put("idOrder","45");
        dataPrint.put("isPaid","ZAPLACONE");
        dataPrint.put("totalPrice","180.00");
        dataPrint.put("rowParams", tableData);

        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/printForm.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, dataPrint, tableData);

        JasperExportManager.exportReportToPdfFile(jasperPrint,"test.pdf");

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        printRequestAttributeSet.add(MediaSizeName.ISO_A5);
        printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
        printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 148, 210, MediaPrintableArea.MM));
        printRequestAttributeSet.add(new Copies(2));

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
        exporter.exportReport();
        //exporter.exportReport();

        return "Hello";
    }

}

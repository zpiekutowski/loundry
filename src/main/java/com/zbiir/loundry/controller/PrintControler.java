package com.zbiir.loundry.controller;

import com.zbiir.loundry.model.PrintObject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;

import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/print")
public class PrintControler {

    @Value("${pinter.name}")
    private String printerName;

    @GetMapping("/list")
    public List<String> listOfPrinters(){
        PrintService [] printServices = PrinterJob.lookupPrintServices();
        List<String> printers = new ArrayList<>();
        for(int i=0; i<printServices.length;i++){
            System.out.println(printServices[i].getName());
            printers.add(printServices[i].getName());
        }


        printers.add("DEF:"+printerName);
        return printers;
    }





    @GetMapping("/order")
    public String printOrder() throws FileNotFoundException, JRException{

        List unitOrders  = new ArrayList();
//        PrintObject p1 = new PrintObject("1");
//        PrintObject p2 = new PrintObject("2");
//        PrintObject p3 = new PrintObject("3");


        PrintObject p1 = new PrintObject("23","1","875", "2",
                "Koszula czarna poplaminona w biale plamy jak damy rade to bedzie ok jak nie to do odbioru " +
                        "a jak nie to do wyrzucenia do smieci","80.00");
        PrintObject p2 = new PrintObject("25","4","950","34","Dywan","120.00");
        PrintObject p3 = new PrintObject("30","7","123","52","Sukienka","60.00");

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

        return "Zadanie";
    }

}

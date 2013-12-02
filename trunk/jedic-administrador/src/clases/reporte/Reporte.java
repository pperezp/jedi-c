/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte;

import java.awt.print.PrinterJob;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Clase utilizada para generar Reportes
 *
 * @author Patricio Pérez Pinto
 */
public class Reporte {

    /**
     * Genera un reporte con un gráfico en PDF
     *
     * @param listaObjetos lista de objetos que irán en el reporte
     * @param nombreArchivoJasper archivo Jasper, para realizar el reporte
     * @param nombreReportePDF nombre del reporte
     * @param parametros parametros adicionales al reporte
     */
    public static void generarGraficoPDF(List listaObjetos, String nombreArchivoJasper, String nombreReportePDF, Map parametros) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(nombreArchivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaObjetos));
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(nombreReportePDF));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera un reporte con un gráfico en HTML
     *
     * @param listaObjetos lista de objetos que irán en el reporte
     * @param nombreArchivoJasper archivo Jasper, para realizar el reporte
     * @param nombreReporteXLS nombre del reporte
     * @param parametros parametros adicionales al reporte
     */
    public static void generarGraficoHTML(List listaObjetos, String nombreArchivoJasper, String nombreReporteXLS, Map parametros) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(nombreArchivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaObjetos));
            JRExporter exporter = new JRHtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(nombreReporteXLS));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera un reporte con un gráfico en XLS
     *
     * @param listaObjetos lista de objetos que irán en el reporte
     * @param nombreArchivoJasper archivo Jasper, para realizar el reporte
     * @param nombreReporteXLS nombre del reporte
     * @param parametros parametros adicionales al reporte
     */
    public static void generarGraficoXLS(List listaObjetos, String nombreArchivoJasper, String nombreReporteXLS, Map parametros) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(nombreArchivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaObjetos));
            JRExporter exporter = new net.sf.jasperreports.engine.export.JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(nombreReporteXLS));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera un reporte con un gráfico por Impresora
     *
     * @param listaObjetos lista de objetos que irán en el reporte
     * @param nombreArchivoJasper archivo Jasper, para realizar el reporte
     * @param parametros parametros adicionales al reporte
     */
    public static void generarGraficoIMPRESORA(List listaObjetos, String nombreArchivoJasper, Map parametros) {
        try {
            PrinterJob pjob = PrinterJob.getPrinterJob();
            if (pjob.printDialog()) {
                JasperReport reporte = (JasperReport) JRLoader.loadObject(nombreArchivoJasper);
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaObjetos));
                JRExporter exporter = new JRPrintServiceExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

                PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
                printServiceAttributeSet.add(new PrinterName(pjob.getPrintService().getName(), null));

                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
                        printRequestAttributeSet);
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
                        printServiceAttributeSet);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
                        Boolean.FALSE);
                exporter.exportReport();
            }
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

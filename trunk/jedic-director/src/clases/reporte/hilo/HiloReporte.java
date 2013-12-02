/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte.hilo;

import clases.reporte.Reporte;
import clases.configuraciones.Ejecutar;
import clases.configuraciones.Guardar;
import clases.configuraciones.P;
import clases.reporte.modelo.KReportes;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloReporte extends Thread{
    private final String sep = P.SEPARADOR;
    private final List lista;
    private final Map parametros;
    private final JLabel lblMensaje;
    private final String ruta;
    private final boolean isImpreso;
    
    public HiloReporte(List lista, Map parametros, JLabel lblMensaje, String ruta, boolean isImpreso){
        this.lista = lista;
        this.parametros = parametros;
        this.lblMensaje = lblMensaje;
        this.ruta = ruta;
        this.isImpreso = isImpreso;
    }
    
    @Override
    public void run(){
        HiloMensaje hm = new HiloMensaje(this, lblMensaje);
        hm.start();
        if(isImpreso){
            Reporte.generarGraficoIMPRESORA(lista, ruta, parametros);
        }else{
            try {
                //            if(Guardar.getExtension().equalsIgnoreCase(".pdf")){
                //                Reporte.generarGraficoPDF(lista, ruta, Guardar.getRuta(), parametros);
                //            }else if (Guardar.getExtension().equalsIgnoreCase(".html")){
                //                Reporte.generarGraficoHTML(lista, ruta, Guardar.getRuta(), parametros);
                //            }else if (Guardar.getExtension().equalsIgnoreCase(".xls")){
                //                Reporte.generarGraficoXLS(lista, ruta, Guardar.getRuta(), parametros);
                //            }
                if(KReportes.extension.equalsIgnoreCase("pdf")){
                    Reporte.generarGraficoPDF(lista, ruta, KReportes.rutaReporte, parametros);
                }else if (KReportes.extension.equalsIgnoreCase("html")){
                    Reporte.generarGraficoHTML(lista, ruta, KReportes.rutaReporte, parametros);
                }else if (KReportes.extension.equalsIgnoreCase("xls")){
                    Reporte.generarGraficoXLS(lista, ruta, KReportes.rutaReporte, parametros);
                }
                System.out.println(Guardar.getExtension());
                System.out.println("RUTA DEL REPORTE -------> "+"\""+KReportes.rutaReporte+"\"");
    //            Ejecutar.ejecutarComando("\""+KReportes.rutaReporte+"\"");
                Desktop.getDesktop().open(new File(KReportes.rutaReporte));
            } catch (IOException ex) {
                Logger.getLogger(HiloReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lblMensaje.setVisible(false);
        hm.interrupt();
    }
}

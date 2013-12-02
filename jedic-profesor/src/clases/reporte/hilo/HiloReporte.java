/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte.hilo;

import clases.reporte.Reporte;
import clases.configuraciones.Ejecutar;
import clases.configuraciones.Guardar;
import clases.configuraciones.P;
import clases.reporte.KReportes;
import java.util.List;
import java.util.Map;
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
    private final boolean isImpreso;
    
    public HiloReporte(List lista, Map parametros, JLabel lblMensaje, boolean isImpreso){
        this.lista = lista;
        this.parametros = parametros;
        this.lblMensaje = lblMensaje;
        this.isImpreso = isImpreso;
    }
    
    @Override
    public void run(){
        HiloMensaje hm = new HiloMensaje(this, lblMensaje);
        hm.start();
        if(isImpreso){
            Reporte.generarGraficoIMPRESORA(lista, "reportes"+sep+"reporteEjerciciosSecciones.jasper", parametros);
        }else{
            if(KReportes.extension.equalsIgnoreCase("pdf")){
                Reporte.generarGraficoPDF(lista, "reportes"+sep+"reporteEjerciciosSecciones.jasper", KReportes.rutaReporte, parametros);
            }else if (KReportes.extension.equalsIgnoreCase("html")){
                Reporte.generarGraficoHTML(lista, "reportes"+sep+"reporteEjerciciosSecciones.jasper", KReportes.rutaReporte, parametros);
            }else if (KReportes.extension.equalsIgnoreCase("xls")){
                Reporte.generarGraficoXLS(lista, "reportes"+sep+"reporteEjerciciosSecciones.jasper", KReportes.rutaReporte, parametros);
            }
            System.out.println(KReportes.rutaReporte);
            hm.interrupt();
            System.out.println("RUTA DEL REPORTE -------> "+"\""+KReportes.rutaReporte+"\"");
            lblMensaje.setVisible(false);
            Ejecutar.ejecutarComando("\""+KReportes.rutaReporte+"\"");
        }
    }
}

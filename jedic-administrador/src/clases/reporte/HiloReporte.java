/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte;

import clases.vista.mensajes.HiloMensajeCondicion;
import clases.reporte.Reporte;
import clases.utilidades.Ejecutar;
import clases.utilidades.P;
import clases.modelo.reporte.KReportes;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;

/**
 * Hilo utilizado para realizar un Reporte sin que se quede pegada la GUI
 *
 * @author Patricio Pérez Pinto
 */
public class HiloReporte extends Thread {

    private final List lista;
    private final Map parametros;
    private final JLabel lblMensaje;
    private final String ruta;
    private final boolean isImpreso;

    /**
     * Constructor para construir un reporte
     *
     * @param lista lista de objetos que irán en el reporte
     * @param parametros parametros adicionales
     * @param lblMensaje JLabel donde ira un mensaje que se esta generando el
     * repore
     * @param ruta ruta del reporte
     * @param isImpreso si es impreso o no
     */
    public HiloReporte(List lista, Map parametros, JLabel lblMensaje, String ruta, boolean isImpreso) {
        this.lista = lista;
        this.parametros = parametros;
        this.lblMensaje = lblMensaje;
        this.ruta = ruta;
        this.isImpreso = isImpreso;
    }

    @Override
    public void run() {
        HiloMensajeCondicion hm = new HiloMensajeCondicion(this, lblMensaje);
        hm.start();

        if (isImpreso) {
            Reporte.generarGraficoIMPRESORA(lista, ruta, parametros);
        } else {
            if (KReportes.extension.equalsIgnoreCase("pdf")) {
                Reporte.generarGraficoPDF(lista, ruta, KReportes.rutaReporte, parametros);
            } else if (KReportes.extension.equalsIgnoreCase("html")) {
                Reporte.generarGraficoHTML(lista, ruta, KReportes.rutaReporte, parametros);
            } else if (KReportes.extension.equalsIgnoreCase("xls")) {
                Reporte.generarGraficoXLS(lista, ruta, KReportes.rutaReporte, parametros);
            }
            System.out.println("RUTA DEL REPORTE -------> " + "\"" + KReportes.rutaReporte + "\"");
            Ejecutar.ejecutarComando("\"" + KReportes.rutaReporte + "\"");
        }
        lblMensaje.setVisible(false);
        hm.interrupt();
    }
}

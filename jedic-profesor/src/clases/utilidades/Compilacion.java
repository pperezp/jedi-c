/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;

import clases.configuraciones.Ejecutar;
import clases.principal.JedicProfesor;
import java.awt.Color;
import javax.swing.JTextArea;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Compilacion {
    public static void compilar(JTextArea salida){
        String res;
        res = Ejecutar.ejecutarComandoGcc(Ruta.RUTA,Ruta.RUTA_SIN_EXTENSION);
        if(res.equalsIgnoreCase("")){

            salida.setForeground(Color.blue);
            salida.setText(Hora.getHoraActual()+" - La compliación de Realizó con Éxito\nArchivo: "+Ruta.RUTA_SIN_EXTENSION+".exe");
            JedicProfesor.dialogCompilacion.setLocationRelativeTo(null);
            JedicProfesor.textoDialogo.setText("Ejecutando...");
            JedicProfesor.dialogCompilacion.setVisible(true);
            Ejecutar.abrirExplorador("\""+Ruta.RUTA_SIN_EXTENSION+"\"");
            JedicProfesor.dialogCompilacion.setVisible(false);
        }else{
            salida.setForeground(Color.red);
            salida.setText(Hora.getHoraActual()+" - "+res);
        }
    }
}

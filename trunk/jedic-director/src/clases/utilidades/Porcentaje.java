/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Porcentaje {
    private static DecimalFormat df;
    
    public static Number getPorcentaje(double cantidad, double total, int cantidadDeDecimales){
        try {
            double porcentaje = (cantidad * 100) /total;
            String decimales = ".";
            for(int i=0; i<cantidadDeDecimales; i++){
                decimales +="#";
            }
            df = new DecimalFormat("##"+(cantidadDeDecimales > 0? decimales: ""));
            Number numero = df.parse(df.format(porcentaje));
            return numero;
        } catch (ParseException ex) {
            Logger.getLogger(Porcentaje.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

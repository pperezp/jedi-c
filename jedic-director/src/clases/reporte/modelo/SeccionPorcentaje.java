/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte.modelo;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class SeccionPorcentaje extends Seccion{
    private double porcentaje;

    public SeccionPorcentaje(int porcentaje, String numSec) {
        super(numSec);
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte.modelo;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ProfesorPorcentaje extends Profesor{
    private double porcentaje;

    public ProfesorPorcentaje(double porcentaje, String nombre) {
        super(nombre);
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}

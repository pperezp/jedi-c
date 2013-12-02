/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.reporte;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class SedePorcentaje extends Sede {

    private double porcentaje;

    /**
     *
     * @param porcentaje porcentaje de uso del software de la sede
     * @param nombre nombre de la sede
     */
    public SedePorcentaje(double porcentaje, String nombre) {
        super(nombre);
        this.porcentaje = porcentaje;
    }

    /**
     *
     * @return el porcentaje de uso del software de la sede
     */
    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * setea el porcentaje
     *
     * @param nuevoPorcentaje el porcentaje nuevo de uso del software
     */
    public void setPorcentaje(double nuevoPorcentaje) {
        this.porcentaje = nuevoPorcentaje;
    }
}

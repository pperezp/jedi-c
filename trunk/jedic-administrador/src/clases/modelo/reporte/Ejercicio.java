/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.reporte;

/**
 *
 * @author Administrador
 */
public class Ejercicio extends Entidad {

    private int cantidad;

    /**
     * un ejercicio tiene una cantidad y un nombre
     *
     * @param cantidad cantidad de ejercicios
     * @param nombre nombre del ejercicio
     */
    public Ejercicio(int cantidad, String nombre) {
        super(nombre);
        this.cantidad = cantidad;
    }

    /**
     *
     * @return la cantidad de ejercicios
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * setea la cantidad de ejercicios
     *
     * @param cantidad la cantidad de ejercicios
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

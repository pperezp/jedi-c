/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.reporte;

/**
 *
 * @author Administrador
 */
public class Entidad {
    private String nombre;

    /**
     * Una entidad tiene un nombre
     * @param nombre nombre de la entidad
     */
    public Entidad(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return el nombre de la entidad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setea el nombre de la entidad
     * @param nombre el nombre de la entidad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

/**
 *
 * @author Pato Pérez
 */
public class Filtro {
    private String nombre;
    private String filtro;

    public Filtro(String nombre, String filtro) {
        this.nombre = nombre;
        this.filtro = filtro;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    
}

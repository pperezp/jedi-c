/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.grafico.modelo;

/**
 *
 * @author Pato Pérez
 */
public class Ente {
    private String nombre;
    private int valorY;

//    public Ente() {
//        this.valorY = 0;
//    }
    
    public Ente(String nombre) {
        this.valorY = 0;
        this.nombre = nombre;
    }
    
    /**
     * Aumenta en uno el valor del ylabel
     */
    public void contar() {
        this.valorY = this.valorY + 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValorY() {
        return valorY;
    }

    public void setValorY(int valorY) {
        this.valorY = valorY;
    }
    
    
    
    
}

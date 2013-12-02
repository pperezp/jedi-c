/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;
import clases.utilidades.Util;

/**
 *
 * @author Administrador
 */
public class Causal {
    private String nombre;
    private String fecha;
    private String causal;
    private String responsable;

    public Causal(String nombre, String fecha, String causal, String responsable) {
        this.nombre = nombre;
        this.fecha = Util.getFechaBonita(fecha);
        this.causal = causal;
        this.responsable = responsable;
    }

    public String getCausal() {
        return causal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString(){
        return fecha;
    }

    public String getResponsable() {
        return responsable;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pato Pérez
 */
public class RegistroHistorial {

    private String nombreResponsable;
    private String accion;
    private Date fecha;
    private SimpleDateFormat formatoFechaLinda;

    public RegistroHistorial(String nombreResponsable, String accion, Date fecha) {
        this.nombreResponsable = nombreResponsable;
        this.accion = accion;
        this.fecha = fecha;
        formatoFechaLinda = new SimpleDateFormat("d 'de' MMMM 'de' y 'a las' HH:mm:ss");
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Date getFechaAsDate() {
        return fecha;
    }
    
    public String getFechaFormateada(){
        return formatoFechaLinda.format(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombre) {
        this.nombreResponsable = nombre;
    }
    
    @Override
    public String toString(){
        return this.getFechaFormateada()+"\t\t\t"+this.accion+"\t"+this.nombreResponsable;
    }
}
